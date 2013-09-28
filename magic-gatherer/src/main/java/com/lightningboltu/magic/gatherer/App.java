package com.lightningboltu.magic.gatherer;

import com.lightningboltu.magic.gatherer.objects.Card;
import com.lightningboltu.magic.gatherer.objects.Edition;
import com.lightningboltu.magic.gatherer.query.IQuery;
import com.lightningboltu.magic.gatherer.query.magiccardsinfo.MagicCardsInfoQuery;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        IQuery query = new MagicCardsInfoQuery();
        List<Edition> editionList = query.retriveAllAvailbleEditions();
        List<Card> cardList = query.retriveCardsByEdition(editionList.get(0));
        System.out.println(cardList.size());
        
    }
}
