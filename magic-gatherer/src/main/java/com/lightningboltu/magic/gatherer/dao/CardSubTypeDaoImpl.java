/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightningboltu.magic.gatherer.dao;

import com.lightningboltu.magic.gatherer.objects.CardSubType;
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
 * @author cedric
 */
@Repository("CardSubTypeDAO")
public class CardSubTypeDaoImpl implements CardSubTypeDao 
{
       private HibernateTemplate hibernateTemplate;
 
    @Autowired
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void saveCardSubType(CardSubType cardSubType) {
        hibernateTemplate.saveOrUpdate(cardSubType);
    }
        
    @Transactional(readOnly = false)
    @Override
    public void saveCardSubTypes(List<CardSubType> cardSubTypes) 
    {
        List<CardSubType> tempSaveList = new LinkedList<CardSubType>();
        for(CardSubType temp : cardSubTypes )
        {
            tempSaveList.add(temp);
            if(tempSaveList.size() % 1000 == 1)
            {
                hibernateTemplate.saveOrUpdateAll(tempSaveList);
                hibernateTemplate.flush();
                hibernateTemplate.clear();
                tempSaveList.clear();
                Logger.getLogger(CardSubTypeDaoImpl.class.getName()).log(Level.INFO,"Card subtype batch save!");
            }
        }
        hibernateTemplate.saveOrUpdateAll(tempSaveList);
    }

    
    @Override
    public List<CardSubType> getAllCardSubTypes() {
         return (List<CardSubType>) hibernateTemplate.find("from "
                + CardSubType.class.getName());
    }
    
    @Override
    public List<CardSubType> getCardSubTypes(int firstResult, int maxResults) 
    {
       DetachedCriteria criteria;
       criteria =  DetachedCriteria.forClass(CardSubType.class);
       criteria.addOrder(Order.asc("card_subtype_id"));
       return (List<CardSubType>) hibernateTemplate.findByCriteria(criteria, firstResult, maxResults);
      
    



    }
    
    @Override
    public List<CardSubType> getCardSubTypes(CardSubType p)
    {
        return (List<CardSubType>) hibernateTemplate.findByCriteria(
        DetachedCriteria.forClass(CardSubType.class)
                .add(Example.create(p)));
    
    }

    @SuppressWarnings("unchecked")
    @Override
    public CardSubType getCardSubTypeById(int cardSubTypeId) {
        return hibernateTemplate.get(CardSubType.class, cardSubTypeId);
    }
    
    
    @Transactional(readOnly = false)
    @Override
    public void deleteCardSubType(CardSubType cardSubType) {
       hibernateTemplate.delete(cardSubType);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void clearCardSubTypes() 
    {
       hibernateTemplate.deleteAll(hibernateTemplate.loadAll(CardSubType.class));
    }    
}
