/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightningboltu.magic.gatherer.query.magiccardsinfo;

import com.lightningboltu.magic.gatherer.objects.Card;
import com.lightningboltu.magic.gatherer.objects.Edition;
import com.lightningboltu.magic.gatherer.query.BaseCardSiteQuery;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                result.addAll(retrieveCardsFromResultPage(d));
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
            List<Card> results =  new LinkedList<Card>();
            Elements cardElements = d.select("hr ~ table[align=center]");
            for (Element cardElement : cardElements)
            {
                Card tmpCard = new Card();
                Elements tdElements =cardElement.select("td");
                Element picElement = tdElements.get(0);
                Element cardTextElement = tdElements.get(1);
                Element printEditionsElement = tdElements.get(2);
                
                tmpCard = fillPicElementData(tmpCard, picElement);
                tmpCard = fillCardTextElementData(tmpCard, cardTextElement);
                tmpCard = fillPrintEditionsElement(tmpCard, printEditionsElement);
                results.add(tmpCard);
                
                
                System.out.println();
            }
            return results;
    }
    private Card fillPicElementData(Card tmpCard, Element picElement) 
    {
        
        return tmpCard;
    }

    private Card fillCardTextElementData(Card tmpCard, Element cardTextElement) 
    {
        String name = cardTextElement.select("span>a").text();
        tmpCard.setName(name);
        System.out.println(name);
        return tmpCard;
    }

    private Card fillPrintEditionsElement(Card tmpCard, Element printEditionsElement) 
    {
        return tmpCard;
    }
    
    
    
}
