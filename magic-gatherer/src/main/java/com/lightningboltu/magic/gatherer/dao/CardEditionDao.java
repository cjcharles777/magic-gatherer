/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightningboltu.magic.gatherer.dao;

import com.lightningboltu.magic.gatherer.objects.CardEdition;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Cedric
 */
public interface CardEditionDao {

    @Transactional(readOnly = false)
    void clearCardEditions();

    @Transactional(readOnly = false)
    void deleteCardEdition(CardEdition cardEdition);

    List<CardEdition> getAllCardEditions();

    @SuppressWarnings(value = "unchecked")
    CardEdition getCardEditionById(int cardEditionId);

    List<CardEdition> getCardEditions(int firstResult, int maxResults);

    List<CardEdition> getCardEditions(CardEdition p);

    @Transactional(readOnly = false)
    void saveCardEdition(CardEdition cardEdition);

    @Transactional(readOnly = false)
    void saveCardEditions(List<CardEdition> cardEditions);

    @Autowired
    void setSessionFactory(SessionFactory sessionFactory);
    
}
