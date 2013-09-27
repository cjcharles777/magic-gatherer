/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightningboltu.magic.gatherer.query;

import com.lightningboltu.magic.gatherer.objects.Card;
import com.lightningboltu.magic.gatherer.objects.Edition;
import java.util.List;

/**
 *
 * @author cedric
 */
public interface IQuery 
{
    public List<Edition> retriveAllAvailbleEditions();
    
    public List<Card> retriveCardsByEdition(Edition e);
    
    public List<Card> retriveCardsByName(String name);
    
    
    
}
