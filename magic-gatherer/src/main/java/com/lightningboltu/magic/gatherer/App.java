package com.lightningboltu.magic.gatherer;

import com.lightningboltu.magic.gatherer.dao.CardDao;
import com.lightningboltu.magic.gatherer.dao.CardEditionDao;
import com.lightningboltu.magic.gatherer.dao.CardSubTypeDao;
import com.lightningboltu.magic.gatherer.dao.CardTypeDao;
import com.lightningboltu.magic.gatherer.dao.EditionDao;
import com.lightningboltu.magic.gatherer.objects.Card;
import com.lightningboltu.magic.gatherer.objects.CardEdition;
import com.lightningboltu.magic.gatherer.objects.CardSubType;
import com.lightningboltu.magic.gatherer.objects.CardType;
import com.lightningboltu.magic.gatherer.objects.Edition;
import com.lightningboltu.magic.gatherer.query.IQuery;
import com.lightningboltu.magic.gatherer.query.magiccardsinfo.MagicCardsInfoQuery;
import com.lightningboltu.magic.gatherer.ui.AdminJFrame;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
     private static CardTypeDao cardTypeDao = (CardTypeDao)applicationContext.getBean("CardTypeDAO");
     private static CardSubTypeDao cardSubTypeDao = (CardSubTypeDao)applicationContext.getBean("CardSubTypeDAO");
    public static void main(String[] args)
    {
                /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminJFrame(applicationContext).setVisible(true);
            }
        });
    }

/**    public static void main2( String[] args )
    {
       
        IQuery query = new MagicCardsInfoQuery();
        List<Edition> editionList = query.retriveAllAvailbleEditions();
        editionDao.saveEditions(editionList);
        List<CardEdition> cardEditionBatchList = new LinkedList<CardEdition>();
        List<Card> cardBatchList = new LinkedList<Card>();
        List <CardType> cardTypeBatchList = new LinkedList<CardType>();
        List <CardSubType> cardSubTypeBatchList = new LinkedList<CardSubType>();
        Map<String, Card> cardMap = new HashMap<String, Card>();
        Map<String, CardType> cardTypeMap = new HashMap<String, CardType>();
        Map<String, CardSubType> cardSubTypeMap = new HashMap<String, CardSubType>();
        
        for(Edition edition : editionList)
        {
            List<Card> cardList = query.retriveCardsByEdition(edition);
            List<CardEdition> cardEditionList = new LinkedList<CardEdition>();
            for(Card card : cardList)
            {
                List<CardType> tempCardTypeList = card.getCardType();
                List<CardType> newCardTypeList = new LinkedList<CardType>();
                for(CardType ct : tempCardTypeList)
                {
                    if(cardTypeMap.containsKey(ct.getName()))
                    {
                        newCardTypeList.add(cardTypeMap.get(ct.getName()));
                    }
                    else
                    {
                        newCardTypeList.add(ct);
                        cardTypeMap.put(ct.getName(), ct);
                        cardTypeBatchList.add(ct);
                    }
                }
                card.setCardType(newCardTypeList);
                
                 List<CardSubType> tempCardSubTypeList = card.getCardSubType();
                List<CardSubType> newCardSubTypeList = new LinkedList<CardSubType>();
                for(CardSubType cst : tempCardSubTypeList)
                {
                    if(cardSubTypeMap.containsKey(cst.getName()))
                    {
                        newCardSubTypeList.add(cardSubTypeMap.get(cst.getName()));
                    }
                    else
                    {
                        newCardSubTypeList.add(cst);
                        cardSubTypeMap.put(cst.getName(), cst);
                        cardSubTypeBatchList.add(cst);
                    }
                }
                card.setCardSubType(newCardSubTypeList);
                
                if(cardMap.containsKey(card.getName()))
                {
                    Card savedCard = cardMap.get(card.getName());
                    savedCard.getCardEditionList().addAll(card.getCardEditionList());
                    cardEditionBatchList.addAll(card.getCardEditionList());
                }
                else
                {
                    cardMap.put(card.getName(), card);
                    cardBatchList.add(card);
                    cardEditionBatchList.addAll(card.getCardEditionList());
                }
                //cardEditionList.addAll(card.getCardEditionList());
            }
            
            
            System.out.println(cardList.size());
        }
        cardEditionDao.saveCardEditions(cardEditionBatchList);
       
        cardTypeDao.saveCardTypes(cardTypeBatchList);
        cardSubTypeDao.saveCardSubTypes(cardSubTypeBatchList);
        
         cardDao.saveCards(cardBatchList);
    } **/
}
