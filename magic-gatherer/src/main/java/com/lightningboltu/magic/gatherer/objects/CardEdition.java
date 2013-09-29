/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightningboltu.magic.gatherer.objects;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Cedric
 */
@Entity
@Table(name = "CardEdition")
public class CardEdition implements Serializable
{
    private Edition edition;
    private String rarity;
    private Integer editionNumber;
    private int id;

    
     @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    @Column(name = "card_edition_id", nullable=false)
    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }
    
    
    @ManyToOne(cascade = CascadeType.ALL)
    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    @Column(name = "name", length=30, nullable=false)
    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    @Column(name = "edition_number", nullable=false)
    public Integer getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(Integer editionNumber) {
        this.editionNumber = editionNumber;
    }
    
    
    
}
