/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightningboltu.magic.gatherer.query.magiccardsinfo;

import com.lightningboltu.magic.gatherer.objects.Card;
import com.lightningboltu.magic.gatherer.objects.Edition;
import com.lightningboltu.magic.gatherer.query.BaseCardSiteQuery;
import java.io.IOException;
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
    private String baseUrl = "http://magiccards.info/";
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

    public List<Card> retriveCardsByEdition(Edition e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Card> retriveCardsByName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
}
