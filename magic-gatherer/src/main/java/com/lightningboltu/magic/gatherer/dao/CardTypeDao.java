/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightningboltu.magic.gatherer.dao;

import com.lightningboltu.magic.gatherer.objects.CardType;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cedric
 */
public interface CardTypeDao {

    @Transactional(readOnly = false)
    void clearCardTypes();

    @Transactional(readOnly = false)
    void deleteCardType(CardType cardType);

    List<CardType> getAllCardTypes();

    @SuppressWarnings(value = "unchecked")
    CardType getCardTypeById(int cardTypeId);

    List<CardType> getCardTypes(int firstResult, int maxResults);

    List<CardType> getCardTypes(CardType p);

    @Transactional(readOnly = false)
    void saveCardType(CardType cardType);

    @Transactional(readOnly = false)
    void saveCardTypes(List<CardType> cardTypes);

    @Autowired
    void setSessionFactory(SessionFactory sessionFactory);
    
}
