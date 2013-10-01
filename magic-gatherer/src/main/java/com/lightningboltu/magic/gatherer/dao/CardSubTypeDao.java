/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightningboltu.magic.gatherer.dao;

import com.lightningboltu.magic.gatherer.objects.CardSubType;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cedric
 */
public interface CardSubTypeDao{

    @Transactional(readOnly = false)
    void clearCardSubTypes();

    @Transactional(readOnly = false)
    void deleteCardSubType(CardSubType cardSubType);

    List<CardSubType> getAllCardSubTypes();

    @SuppressWarnings(value = "unchecked")
    CardSubType getCardSubTypeById(int cardSubTypeId);

    List<CardSubType> getCardSubTypes(int firstResult, int maxResults);

    List<CardSubType> getCardSubTypes(CardSubType p);

    @Transactional(readOnly = false)
    void saveCardSubType(CardSubType cardSubType);

    @Transactional(readOnly = false)
    void saveCardSubTypes(List<CardSubType> cardSubTypes);

    @Autowired
    void setSessionFactory(SessionFactory sessionFactory);
    
}
