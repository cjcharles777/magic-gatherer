package com.lightningboltu.magic.gatherer;

import com.lightningboltu.magic.gatherer.dao.CardDao;
import com.lightningboltu.magic.gatherer.dao.CardDaoImpl;
import com.lightningboltu.magic.gatherer.dao.CardEditionDao;
import com.lightningboltu.magic.gatherer.dao.CardEditionDaoImpl;
import com.lightningboltu.magic.gatherer.dao.EditionDao;
import com.lightningboltu.magic.gatherer.dao.EditionDaoImpl;
import com.lightningboltu.magic.gatherer.objects.Card;
import com.lightningboltu.magic.gatherer.objects.CardEdition;
import com.lightningboltu.magic.gatherer.objects.Edition;
import com.lightningboltu.magic.gatherer.query.IQuery;
import com.lightningboltu.magic.gatherer.query.magiccardsinfo.MagicCardsInfoQuery;
import java.util.LinkedList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
     private static final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("app-config.xml");
     private static CardDao cardDao = (CardDao)applicationContext.getBean("CardDAO");
     private static CardEditionDao cardEditionDao = (CardEditionDao)applicationContext.getBean("CardEditionDAO");
     private static EditionDao editionDao = (EditionDao)applicationContext.getBean("EditionDAO");
    public static void main( String[] args )
    {
       
        IQuery query = new MagicCardsInfoQuery();
        List<Edition> editionList = query.retriveAllAvailbleEditions();
        editionDao.saveEditions(editionList);
        for(Edition edition : editionList)
        {
            List<Card> cardList = query.retriveCardsByEdition(edition);
            List<CardEdition> cardEditionList = new LinkedList<CardEdition>();
            for(Card card : cardList)
            {
                cardEditionList.addAll(card.getCardEditionList());
            }
            
            cardEditionDao.saveCardEditions(cardEditionList);
            cardDao.saveCards(cardList);
            System.out.println(cardList.size());
        }
    }
}
