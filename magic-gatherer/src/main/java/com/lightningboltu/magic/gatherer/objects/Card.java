/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightningboltu.magic.gatherer.objects;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;

/**
 *
 * @author cedric
 */
@Entity
@Table(name = "Cards")
public class Card implements Serializable
{
    private int id;
    private String name;
    private String cardType;
    private String manaCost;
    private Integer power;
    private Integer toughness;
    private Integer convertedManaCost;
    private Integer loyalty;
    private String colorIndicator;
    List<CardEdition> cardEditionList = new LinkedList<CardEdition>();
    private String cardText;
    private String flavorText;
    private String artistText;
    private byte[] image;

    
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
    
    @Column(name = "name", length=100, nullable=false)
    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    @Column(name = "card_type", length=100, nullable=false)
    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Column(name = "mana_cost", length=100)
    public String getManaCost() {
        return manaCost;
    }

    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }

    @Column(name = "power")
    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    @Column(name = "toughness")
    public Integer getToughness() {
        return toughness;
    }

    public void setToughness(Integer toughness) {
        this.toughness = toughness;
    }

     @Column(name = "converted_mana_cost")
    public Integer getConvertedManaCost() {
        return convertedManaCost;
    }

    public void setConvertedManaCost(Integer convertedManaCost) {
        this.convertedManaCost = convertedManaCost;
    }
    
    @Column(name = "loyalty")
    public Integer getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(Integer loyalty) {
        this.loyalty = loyalty;
    }

    @OneToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable(
            name="CardToCardEdition",
            joinColumns = @JoinColumn( name="card_id"),
            inverseJoinColumns = @JoinColumn( name="edition_id")
    )
     @LazyCollection(LazyCollectionOption.FALSE)
    public List<CardEdition> getCardEditionList() {
        return cardEditionList;
    }

    public void setCardEditionList(List<CardEdition> cardEditionList) {
        this.cardEditionList = cardEditionList;
    }

    @Column(name = "card_text", length=2000)
    public String getCardText() {
        return cardText;
    }

    public void setCardText(String cardText) {
        this.cardText = cardText;
    }

    @Column(name = "flavor_text", length=2000)
    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    @Column(name = "artist_text", length=200)
    public String getArtistText() {
        return artistText;
    }

    public void setArtistText(String artistText) {
        this.artistText = artistText;
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
    
    @Column(name = "color_indicator", length=200)
    public String getColorIndicator() {
        return colorIndicator;
    }

    public void setColorIndicator(String colorIndicator) {
        this.colorIndicator = colorIndicator;
    }


    
    
    
    
    
}
