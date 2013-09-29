/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightningboltu.magic.gatherer.dao;

import com.lightningboltu.magic.gatherer.objects.Edition;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Cedric
 */
public interface EditionDao {

    @Transactional(readOnly = false)
    void clearEditions();

    @Transactional(readOnly = false)
    void deleteEdition(Edition edition);

    List<Edition> getAllEditions();

    @SuppressWarnings(value = "unchecked")
    Edition getEditionById(int editionId);

    List<Edition> getEditions(int firstResult, int maxResults);

    List<Edition> getEditions(Edition p);

    @Transactional(readOnly = false)
    void saveEdition(Edition edition);

    @Transactional(readOnly = false)
    void saveEditions(List<Edition> editions);

    @Autowired
    void setSessionFactory(SessionFactory sessionFactory);
    
}
