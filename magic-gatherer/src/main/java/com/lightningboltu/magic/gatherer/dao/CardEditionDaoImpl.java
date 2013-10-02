/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightningboltu.magic.gatherer.dao;

import com.lightningboltu.magic.gatherer.objects.CardEdition;
import com.lightningboltu.magic.gatherer.objects.CardType;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Cedric
 */
@Repository("CardEditionDAO")
@Transactional
public class CardEditionDaoImpl implements CardEditionDao 
{
     private HibernateTemplate hibernateTemplate;
 
    @Autowired
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void saveCardEdition(CardEdition cardEdition) {
        hibernateTemplate.saveOrUpdate(cardEdition);
    }
        
    @Transactional(readOnly = false)
    @Override
    public void saveCardEditions(List<CardEdition> cardEditions) 
    {
        List<CardEdition> tempSaveList = new LinkedList<CardEdition>();
        for(CardEdition temp : cardEditions )
        {
            tempSaveList.add(temp);
            if(tempSaveList.size() % 1000 == 1)
            {
                hibernateTemplate.saveOrUpdateAll(tempSaveList);
                hibernateTemplate.flush();
                hibernateTemplate.clear();
                tempSaveList.clear();
                Logger.getLogger(CardEditionDaoImpl.class.getName()).log(Level.INFO,"Card edition batch save!");
            }
        }
        hibernateTemplate.saveOrUpdateAll(tempSaveList);
    }

    
    @Override
    public List<CardEdition> getAllCardEditions() {
         return (List<CardEdition>) hibernateTemplate.find("from "
                + CardEdition.class.getName());
    }
    
    @Override
    public List<CardEdition> getCardEditions(int firstResult, int maxResults) 
    {
       DetachedCriteria criteria;
       criteria =  DetachedCriteria.forClass(CardEdition.class);
       criteria.addOrder(Order.asc("card_edition_id"));
       return (List<CardEdition>) hibernateTemplate.findByCriteria(criteria, firstResult, maxResults);
      
    



    }
    
    @Override
    public List<CardEdition> getCardEditions(CardEdition p)
    {
        return (List<CardEdition>) hibernateTemplate.findByCriteria(
        DetachedCriteria.forClass(CardEdition.class)
                .add(Example.create(p)));
    
    }

    @SuppressWarnings("unchecked")
    @Override
    public CardEdition getCardEditionById(int cardEditionId) {
        return hibernateTemplate.get(CardEdition.class, cardEditionId);
    }
    
    
    @Transactional(readOnly = false)
    @Override
    public void deleteCardEdition(CardEdition cardEdition) {
       hibernateTemplate.delete(cardEdition);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void clearCardEditions() 
    {
       hibernateTemplate.deleteAll(hibernateTemplate.loadAll(CardEdition.class));
    }    
    
}
