/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightningboltu.magic.gatherer.query.magiccardsinfo;

import com.lightningboltu.magic.gatherer.objects.Card;
import com.lightningboltu.magic.gatherer.objects.CardEdition;
import com.lightningboltu.magic.gatherer.objects.CardSubType;
import com.lightningboltu.magic.gatherer.objects.CardType;
import com.lightningboltu.magic.gatherer.objects.Edition;
import com.lightningboltu.magic.gatherer.query.BaseCardSiteQuery;
import com.lightningboltu.magic.gatherer.util.ImageUtil;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author cedric
 */
public class MagicCardsInfoQuery extends BaseCardSiteQuery
{
    private String baseUrl = "http://magiccards.info/query?q=";
    private String urlEnd = "&v=card&s=cname";
    private String expansionsUrl = "http://magiccards.info/sitemap.html";
    
    public List<Edition> retriveAllAvailbleEditions() 
    {
        List<Edition> result = new LinkedList<Edition>();
        try 
        {
            Document d = getQueryResults(expansionsUrl);
             Elements elements = d.select("table").get(1).select("td>ul>li>ul>li");
             
            for (Element element : elements) 
            {
                Edition tempEdition = new Edition();
                tempEdition.setDisplay(element.select("a").text());
                tempEdition.setCode(element.select("small").text());
                result.add(tempEdition);
            }
            
            
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(MagicCardsInfoQuery.class.getName()).log(Level.SEVERE, null, ex);
            //Should not happen but hey...
        }
        return result;
    }

    public List<Card> retriveCardsByEdition(Edition e) 
    {
        List<Card> result = new LinkedList<Card>();
        try 
        {
            String searchParam = URLEncoder.encode("e:"+e.getCode()+"/en", "UTF-8");
            String searchUrl = baseUrl+searchParam+urlEnd;
            
       
            boolean next;
            do
            {
                Document d = getQueryResults(searchUrl);
                result.addAll(retrieveCardsFromResultPage(d, e));
                Element nextPageElement = d.select("table").last().select("td").first();
                String nextPage = nextPageElement.text();
                next = nextPage.startsWith("next");
                if(next)
                {
                    Element nextUrlElement = nextPageElement.select("a").first();
                    searchUrl = nextUrlElement.attr("abs:href");
                }
            }
            while(next);
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(MagicCardsInfoQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }

    public List<Card> retriveCardsByName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    private List<Card> retrieveCardsFromResultPage(Document d)
    {
        return retrieveCardsFromResultPage(d, null);
    } 

    private List<Card> retrieveCardsFromResultPage(Document d, Edition e)
    {
            List<Card> results =  new LinkedList<Card>();
            Elements cardElements = d.select("hr ~ table[align=center]");
            for (Element cardElement : cardElements)
            {
                Card tmpCard = new Card();
                Elements tdElements =cardElement.select("td");
                Element picElement = tdElements.get(0);
                Element cardTextElement = tdElements.get(1);
                Element printEditionsElement = tdElements.get(2);
                
                //
                tmpCard = fillCardTextElementData(tmpCard, cardTextElement);
                tmpCard = fillPrintandPicEditionsElement(tmpCard, printEditionsElement, picElement, e);
                results.add(tmpCard);
                
                
                System.out.println();
            }
            return results;
    }
    private CardEdition fillPicElementData(CardEdition tmpCardEdition, Element picElement) 
    {
        Element cardImgElement = picElement.select("img").first();
        String imgUrl = cardImgElement.attr("abs:src");
        System.out.println("Card Image Url : "+ imgUrl);
        try 
        {
            byte[] cardImg = ImageUtil.convertInternetImageToByteArray(imgUrl);
            tmpCardEdition.setImage(cardImg);
        }
        catch (IOException ex) 
        {
            Logger.getLogger(MagicCardsInfoQuery.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Image issue");
        }
        return tmpCardEdition;
    }

    private Card fillCardTextElementData(Card tmpCard, Element cardTextElement) 
    {
        String name = cardTextElement.select("span>a").text();
        Elements cardDetailElements = cardTextElement.select("p");
        String cardStats = cardDetailElements.get(0).text();
        String cardText = cardDetailElements.get(1).html();
        String flavorText = cardDetailElements.get(2).text();
        
        tmpCard.setName(name);
        System.out.println("Name : " +name);
        tmpCard = decypherCardStats(tmpCard, cardStats);
        tmpCard.setCardText(cardText);
        System.out.println("Card Text : " +cardText);
        tmpCard.setFlavorText(flavorText);
        System.out.println("Flavor Text : " +flavorText);
        
        
        return tmpCard;
    }

    private Card fillPrintandPicEditionsElement(Card tmpCard, Element printEditionsElement, 
            Element picElement, Edition e) 
    {
        Elements printEditionPartsElements = printEditionsElement.select("small > b");
        
        CardEdition tempCardEdition = new CardEdition();
        String printNumSectionTxt = printEditionPartsElements.get(0).text();
        System.out.println(printNumSectionTxt);
        String editionRarityTxt = printEditionPartsElements.get(1).text();
        System.out.println(editionRarityTxt);
        
        if(e != null)
        {
            tempCardEdition.setEdition(e);
            System.out.println("Edition: " +e.getDisplay());
        }
        else
        {
            
        }
                
        int printNumStart = printNumSectionTxt.indexOf("#");
        int printNumEnd = printNumSectionTxt.indexOf("(");
        int artistEnd = printNumSectionTxt.indexOf(")");
        int rarityStart = editionRarityTxt.indexOf("(");
        int rarityEnd = editionRarityTxt.indexOf(")");
        String printNumStr = printNumSectionTxt.substring(printNumStart+1, printNumEnd-1);
        String rarityStr = editionRarityTxt.substring(rarityStart+1, rarityEnd);
        String artistText =  printNumSectionTxt.substring(printNumEnd+1,artistEnd);
        tempCardEdition.setEditionNumber(printNumStr);
        System.out.println("Edition # : " +printNumStr);
        tempCardEdition.setRarity(rarityStr);
        System.out.println("Rarity : " + rarityStr );
        tempCardEdition.setArtistText(artistText);
        System.out.println("Artist Text : " +artistText);
        
        tempCardEdition = fillPicElementData(tempCardEdition, picElement);
        
        
        tmpCard.getCardEditionList().add(tempCardEdition);
        return tmpCard;
    }

    private Card decypherCardStats(Card tmpCard, String cardStats) 
    {
        System.out.println(cardStats);
        String[] cardStatsArray = cardStats.split(",");
        String cardTypeSectionStr = cardStatsArray[0];
        ExtractionResults extractResults;
        
        if(cardTypeSectionStr.contains("—"))
        {
            int creatureDelimIndex = cardTypeSectionStr.indexOf("—");
            String tempRemaining = cardTypeSectionStr.substring(creatureDelimIndex);
            if(tempRemaining.contains("("))
            {
                int end = cardTypeSectionStr.indexOf("(");
                int loyaltyEnd = cardTypeSectionStr.indexOf(")");
                int loyaltyStart = cardTypeSectionStr.indexOf(":");
                String specialType = cardTypeSectionStr.substring(end+1, loyaltyStart);
                if(specialType.equalsIgnoreCase("Loyalty"))
                {
                    String loyaltyStr = cardTypeSectionStr.substring(loyaltyStart+1,loyaltyEnd).trim();
                    extractResults = extractCardTypes(cardTypeSectionStr.substring(0,end));
                    tmpCard.setCardType(extractResults.getCardTypeList());
                    tmpCard.setCardSubType(extractResults.getCardSubTypeList());
                    System.out.println("Card Type : " +cardTypeSectionStr.substring(0,end));
                    tmpCard.setLoyalty(Integer.parseInt(loyaltyStr));
                    System.out.println("Loyalty: "+ loyaltyStr);
                }
                if(specialType.equalsIgnoreCase("Color Indicator"))
                {
                                    
                    int i = 0;
                    while ((!Character.isDigit(cardTypeSectionStr.charAt(i)) )&& i < cardTypeSectionStr.length()-1)
                    {
                        i++;
                    }
                    extractResults = extractCardTypes(cardTypeSectionStr.substring(0,i-1));
                    
                    tmpCard.setCardType(extractResults.getCardTypeList());
                    tmpCard.setCardSubType(extractResults.getCardSubTypeList());
                    System.out.println("Card Type : " +cardTypeSectionStr.substring(0,i-1));
                    Matcher matcher = Pattern.compile("\\d+/\\d+").matcher(tempRemaining);
                    if(matcher.find())
                    {
                        String powerTough = matcher.group();
                        System.out.println("Power / Tough : "+powerTough);
                        String[] powerToughArray = powerTough.split("/");
                        tmpCard.setPower(Integer.parseInt(powerToughArray[0]));
                        System.out.println("Power : "+powerToughArray[0]);
                        tmpCard.setToughness(Integer.parseInt(powerToughArray[1]));
                        System.out.println("Tough : "+powerToughArray[1]);
                    }
                    
                     String colorIndicatorStr = cardTypeSectionStr.substring(loyaltyStart+1,loyaltyEnd).trim();
                      tmpCard.setColorIndicator(colorIndicatorStr);
                      System.out.println("Color Indicator: "+ colorIndicatorStr);
                }
            }
            else if (tempRemaining.matches(".*\\d/\\d.*"))
            {
                
                int i = 0;
                while (!Character.isDigit(cardTypeSectionStr.charAt(i)))
                {
                    i++;
                }
               
                extractResults = extractCardTypes(cardTypeSectionStr.substring(0,i-1));
                tmpCard.setCardType(extractResults.getCardTypeList());
                tmpCard.setCardSubType(extractResults.getCardSubTypeList());
                System.out.println("Card Type : " +cardTypeSectionStr.substring(0,i-1));
                Matcher matcher = Pattern.compile("\\d+/\\d+").matcher(tempRemaining);
                if(matcher.find())
                {
                    String powerTough = matcher.group();
                    System.out.println("Power / Tough : "+powerTough);
                    String[] powerToughArray = powerTough.split("/");
                    tmpCard.setPower(Integer.parseInt(powerToughArray[0]));
                    System.out.println("Power : "+powerToughArray[0]);
                    tmpCard.setToughness(Integer.parseInt(powerToughArray[1]));
                    System.out.println("Tough : "+powerToughArray[1]);
                }
            }
            else
            {
                 extractResults = extractCardTypes(cardTypeSectionStr);
                tmpCard.setCardType(extractResults.getCardTypeList());
                tmpCard.setCardSubType(extractResults.getCardSubTypeList());
                 System.out.println("Card Type : "+cardTypeSectionStr);
            }
            
        }
        else
        {
            String extractInput = cardTypeSectionStr;
            if(cardTypeSectionStr.contains("(Color Indicator:"))
            {
                int start = cardTypeSectionStr.indexOf("(Color");
                int colon = cardTypeSectionStr.indexOf(":");
                int end = cardTypeSectionStr.indexOf(")");
                
                String colorIndicatorStr = cardTypeSectionStr.substring(colon+1,end).trim();
                tmpCard.setColorIndicator(colorIndicatorStr);
                System.out.println("Color Indicator: "+ colorIndicatorStr);
               extractInput = cardTypeSectionStr.substring(0, start) + 
                       cardTypeSectionStr.substring(end+1);
                   
            }
            extractResults = extractCardTypes(cardTypeSectionStr);
            tmpCard.setCardType(extractResults.getCardTypeList());
            tmpCard.setCardSubType(extractResults.getCardSubTypeList());
            System.out.println("Card Type : "+cardTypeSectionStr);
        }
        
        if(cardStatsArray.length > 1)
        {
            String manaSectionStr = cardStatsArray[1];
            if(manaSectionStr.contains("("))
            {
                if(!manaSectionStr.contains("Color Indicator:"))
                {
                    int startIndex = manaSectionStr.indexOf("(");
                    int endIndex = manaSectionStr.indexOf(")");
                    String convertedManaStr = manaSectionStr.substring(startIndex+1, endIndex);
                    tmpCard.setConvertedManaCost(Integer.parseInt(convertedManaStr));
                    System.out.println("Converted Mana Cost : " + Integer.parseInt(convertedManaStr));
                    tmpCard.setManaCost(manaSectionStr.substring(0,startIndex));
                    System.out.println("Mana Cost : " + manaSectionStr.substring(0,startIndex));
                }
                else
                {
               
                    int indicatorStart = manaSectionStr.indexOf(":"); //Intervention Pact;
                    int indicatorEnd = manaSectionStr.indexOf(")"); //Intervention Pact;
                    String colorIndicatorStr = manaSectionStr.substring(indicatorStart+1,indicatorEnd).trim();
                    tmpCard.setColorIndicator(colorIndicatorStr);
                    System.out.println("Color Indicator: "+ colorIndicatorStr);
                    tmpCard.setConvertedManaCost(0);
                    System.out.println("Converted Mana Cost : " + 0);
                    
                }
            }
            else
            {
                tmpCard.setConvertedManaCost(0);
                System.out.println("Converted Mana Cost : " + 0);
                tmpCard.setManaCost(manaSectionStr);
                System.out.println("Mana Cost : " + manaSectionStr);
            }
        }
        
        return tmpCard;
    }
    
    private ExtractionResults extractCardTypes(String s)
    {
        ExtractionResults result = new ExtractionResults();
        String cardTypeString = s;
        if(s.contains("—"))
        {
            String[] cardStatsArray = s.split("—");
            cardTypeString = cardStatsArray[0];
           
            if(cardStatsArray.length == 2)
            {
                String[] cardSubTypeArray = cardStatsArray[1].split(" ");
                for (String cs : cardSubTypeArray)
                {
                    CardSubType tempCardSubType = new CardSubType();
                    tempCardSubType.setName(cs);
                    result.getCardSubTypeList().add(tempCardSubType);
                }
            }
        }
        
         String[] cardTypeArray = cardTypeString.split(" ");
         for (String ct : cardTypeArray)
        {
            CardType tempCardType = new CardType();
            tempCardType.setName(ct);
            result.getCardTypeList().add(tempCardType);
        }
        
        
        
        
        return result;
    }

    private static class ExtractionResults 
    {
            List<CardType> cardTypeList =  new LinkedList<CardType>();
            List<CardSubType> cardSubTypeList  =  new LinkedList<CardSubType>();
            
        public ExtractionResults() 
        {
            
            
        }

        public List<CardType> getCardTypeList() {
            return cardTypeList;
        }

        public void setCardTypeList(List<CardType> cardTypeList) {
            this.cardTypeList = cardTypeList;
        }

        public List<CardSubType> getCardSubTypeList() {
            return cardSubTypeList;
        }

        public void setCardSubTypeList(List<CardSubType> cardSubTypeList) {
            this.cardSubTypeList = cardSubTypeList;
        }
        
    }
    
}
