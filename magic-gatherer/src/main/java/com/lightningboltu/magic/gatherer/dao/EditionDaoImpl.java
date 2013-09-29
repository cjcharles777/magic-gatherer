/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightningboltu.magic.gatherer.dao;

import com.lightningboltu.magic.gatherer.objects.Edition;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Cedric
 */
public class EditionDaoImpl implements EditionDao 
{
 private HibernateTemplate hibernateTemplate;
 
    @Autowired
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void saveEdition(Edition edition) {
        hibernateTemplate.saveOrUpdate(edition);
    }
        
    @Transactional(readOnly = false)
    @Override
    public void saveEditions(List<Edition> editions) 
    {
        hibernateTemplate.saveOrUpdateAll(editions);
    }

    
    @Override
    public List<Edition> getAllEditions() {
         return (List<Edition>) hibernateTemplate.find("from "
                + Edition.class.getName());
    }
    
    @Override
    public List<Edition> getEditions(int firstResult, int maxResults) 
    {
       DetachedCriteria criteria;
       criteria =  DetachedCriteria.forClass(Edition.class);
       criteria.addOrder(Order.asc("edition_id"));
       return (List<Edition>) hibernateTemplate.findByCriteria(criteria, firstResult, maxResults);
      
    



    }
    
    @Override
    public List<Edition> getEditions(Edition p)
    {
        return (List<Edition>) hibernateTemplate.findByCriteria(
        DetachedCriteria.forClass(Edition.class)
                .add(Example.create(p))
                .createCriteria("name"));
    
    }

    @SuppressWarnings("unchecked")
    @Override
    public Edition getEditionById(int editionId) {
        return hibernateTemplate.get(Edition.class, editionId);
    }
    
    
    @Transactional(readOnly = false)
    @Override
    public void deleteEdition(Edition edition) {
       hibernateTemplate.delete(edition);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void clearEditions() 
    {
       hibernateTemplate.deleteAll(hibernateTemplate.loadAll(Edition.class));
    }        
    
}
