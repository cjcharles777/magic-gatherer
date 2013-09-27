/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightningboltu.magic.gatherer.query;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author cedric
 */
public abstract class BaseCardSiteQuery implements IQuery
{

    public Document getQueryResults (String url) throws IOException
    {
      return Jsoup.connect(url).get();
    }
    
    
}
