/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightningboltu.magic.gatherer.dao;

import com.lightningboltu.magic.gatherer.objects.Card;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Cedric
 */
public interface CardDao {

    @Transactional(readOnly = false)
    void clearCards();

    @Transactional(readOnly = false)
    void deleteCard(Card card);

    List<Card> getAllCards();

    @SuppressWarnings(value = "unchecked")
    Card getCardById(int cardId);

    List<Card> getCards(int firstResult, int maxResults);

    List<Card> getCards(Card p);

    @Transactional(readOnly = false)
    void saveCard(Card card);

    @Transactional(readOnly = false)
    void saveCards(List<Card> cards);
    
}
