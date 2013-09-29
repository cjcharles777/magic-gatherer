/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightningboltu.magic.gatherer.dao;

import com.lightningboltu.magic.gatherer.objects.Card;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Cedric
 */
@Repository("CardDAO")
@Transactional
public class CardDaoImpl implements CardDao  
{
    private HibernateTemplate hibernateTemplate;
 
    @Autowired
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void saveCard(Card card) {
        hibernateTemplate.saveOrUpdate(card);
    }
        
    @Transactional(readOnly = false)
    @Override
    public void saveCards(List<Card> cards) 
    {
        hibernateTemplate.saveOrUpdateAll(cards);
    }

    
    @Override
    public List<Card> getAllCards() {
         return (List<Card>) hibernateTemplate.find("from "
                + Card.class.getName());
    }
    
    @Override
    public List<Card> getCards(int firstResult, int maxResults) 
    {
       DetachedCriteria criteria;
       criteria =  DetachedCriteria.forClass(Card.class);
       criteria.addOrder(Order.asc("card_id"));
       return (List<Card>) hibernateTemplate.findByCriteria(criteria, firstResult, maxResults);
      
    



    }
    
    @Override
    public List<Card> getCards(Card p)
    {
        return (List<Card>) hibernateTemplate.findByCriteria(
        DetachedCriteria.forClass(Card.class)
                .add(Example.create(p))
                .createCriteria("name")
                .add(Example.create(p.getName())));
    
    }

    @SuppressWarnings("unchecked")
    @Override
    public Card getCardById(int cardId) {
        return hibernateTemplate.get(Card.class, cardId);
    }
    
    
    @Transactional(readOnly = false)
    @Override
    public void deleteCard(Card card) {
       hibernateTemplate.delete(card);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void clearCards() 
    {
       hibernateTemplate.deleteAll(hibernateTemplate.loadAll(Card.class));
    }    
    
}
