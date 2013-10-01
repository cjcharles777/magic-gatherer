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
import javax.persistence.Lob;
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
    private String editionNumber;
    private int id;
    private byte[] image;
    private String artistText;

    
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

    @Column(name = "rarity", length=30, nullable=false)
    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    @Column(name = "edition_number", length=30, nullable=false)
    public String getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(String editionNumber) {
        this.editionNumber = editionNumber;
    }
    
        /**
     *
     * @return
     */
    @Column(name="image", columnDefinition="mediumblob")
    @Lob   
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    
    @Column(name = "artist_text", length=200)
    public String getArtistText() {
        return artistText;
    }

    public void setArtistText(String artistText) {
        this.artistText = artistText;
    }
    
}
