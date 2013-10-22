/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightningboltu.magic.gatherer.objects;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author cedric
 */
@Entity
@Cache(region="Cards", usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Table(name = "Cards")
public class Card implements Serializable
{
    private int id;
    private String name;
    private List<CardType> cardType= new LinkedList<CardType>();
    private List<CardSubType> cardSubType = new LinkedList<CardSubType>();
    private String manaCost;
    private Integer power;
    private Integer toughness;
    private Integer convertedManaCost;
    private Integer loyalty;
    private String colorIndicator;
    List<CardEdition> cardEditionList = new LinkedList<CardEdition>();
    private String cardText;
    private String flavorText;
   
   

    
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
    
    @Column(name = "name", length=250, nullable=false)
    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    @ManyToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable(
            name="CardToCardType",
            joinColumns = @JoinColumn( name="card_id"),
            inverseJoinColumns = @JoinColumn( name="card_type_id")
    )
    @BatchSize(size = 20) 
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<CardType>  getCardType() {
        return cardType;
    }

    public void setCardType(List<CardType> cardType) {
        this.cardType = cardType;
    }
    @ManyToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable(
            name="CardToCardSubType",
            joinColumns = @JoinColumn( name="card_id"),
            inverseJoinColumns = @JoinColumn( name="card_subtype_id")
    )
    @BatchSize(size = 20) 
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<CardSubType>  getCardSubType() {
        return cardSubType;
    }

    public void setCardSubType(List<CardSubType> cardSubType) {
        this.cardSubType = cardSubType;
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
    @BatchSize(size = 20) 
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<CardEdition> getCardEditionList() {
        return cardEditionList;
    }

    public void setCardEditionList(List<CardEdition> cardEditionList) {
        this.cardEditionList = cardEditionList;
    }

    @Column(name = "rules_text", length=2000)
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


    

    
    @Column(name = "color_indicator", length=200)
    public String getColorIndicator() {
        return colorIndicator;
    }

    public void setColorIndicator(String colorIndicator) {
        this.colorIndicator = colorIndicator;
    }


    
    
    
    
    
}
