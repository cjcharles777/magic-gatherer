/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightningboltu.magic.gatherer.dao;

import com.lightningboltu.magic.gatherer.objects.CardType;
import com.lightningboltu.magic.gatherer.query.magiccardsinfo.MagicCardsInfoQuery;
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
@Repository("CardTypeDAO")
@Transactional
public class CardTypeDaoImpl implements CardTypeDao 
{
       private HibernateTemplate hibernateTemplate;
 
    @Autowired
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void saveCardType(CardType cardType) {
        hibernateTemplate.saveOrUpdate(cardType);
    }
        
    @Transactional(readOnly = false)
    @Override
    public void saveCardTypes(List<CardType> cardTypes) 
    {
        List<CardType> tempSaveList = new LinkedList<CardType>();
        for(CardType temp : cardTypes )
        {
            tempSaveList.add(temp);
            if(tempSaveList.size() % 1000 == 1)
            {
                hibernateTemplate.saveOrUpdateAll(tempSaveList);
                hibernateTemplate.flush();
                hibernateTemplate.clear();
                tempSaveList.clear();
                Logger.getLogger(CardTypeDaoImpl.class.getName()).log(Level.INFO,"Card type batch save!");
            }
        }
        hibernateTemplate.saveOrUpdateAll(tempSaveList);
    }

    
    @Override
    public List<CardType> getAllCardTypes() {
         return (List<CardType>) hibernateTemplate.find("from "
                + CardType.class.getName());
    }
    
    @Override
    public List<CardType> getCardTypes(int firstResult, int maxResults) 
    {
       DetachedCriteria criteria;
       criteria =  DetachedCriteria.forClass(CardType.class);
       criteria.addOrder(Order.asc("card_type_id"));
       return (List<CardType>) hibernateTemplate.findByCriteria(criteria, firstResult, maxResults);
      
    



    }
    
    @Override
    public List<CardType> getCardTypes(CardType p)
    {
        return (List<CardType>) hibernateTemplate.findByCriteria(
        DetachedCriteria.forClass(CardType.class)
                .add(Example.create(p)));
    
    }

    @SuppressWarnings("unchecked")
    @Override
    public CardType getCardTypeById(int cardTypeId) {
        return hibernateTemplate.get(CardType.class, cardTypeId);
    }
    
    
    @Transactional(readOnly = false)
    @Override
    public void deleteCardType(CardType cardType) {
       hibernateTemplate.delete(cardType);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void clearCardTypes() 
    {
       hibernateTemplate.deleteAll(hibernateTemplate.loadAll(CardType.class));
    }    
}
