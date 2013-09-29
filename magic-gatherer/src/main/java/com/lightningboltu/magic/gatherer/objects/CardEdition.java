/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightningboltu.magic.gatherer.objects;

/**
 *
 * @author Cedric
 */
public class CardEdition 
{
    private Edition edition;
    private String rarity;
    private Integer editionNumber;

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public Integer getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(Integer editionNumber) {
        this.editionNumber = editionNumber;
    }
    
    
    
}
