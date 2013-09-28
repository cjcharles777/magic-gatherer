/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightningboltu.magic.gatherer.objects;

import java.util.List;

/**
 *
 * @author cedric
 */
public class Card 
{
    private String name;
    private String cardType;
    private String manaCost;
    private Integer power;
    private Integer toughness;
    private Integer convertedManaCost;
    private Integer loyalty;
    List<CardEdition> cardEditionList;
    private String cardText;
    private String flavorText;
    private String artistText;
    
    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getManaCost() {
        return manaCost;
    }

    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getToughness() {
        return toughness;
    }

    public void setToughness(Integer toughness) {
        this.toughness = toughness;
    }

    public Integer getConvertedManaCost() {
        return convertedManaCost;
    }

    public void setConvertedManaCost(Integer convertedManaCost) {
        this.convertedManaCost = convertedManaCost;
    }

    public Integer getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(Integer loyalty) {
        this.loyalty = loyalty;
    }

    public List<CardEdition> getCardEditionList() {
        return cardEditionList;
    }

    public void setCardEditionList(List<CardEdition> cardEditionList) {
        this.cardEditionList = cardEditionList;
    }

    public String getCardText() {
        return cardText;
    }

    public void setCardText(String cardText) {
        this.cardText = cardText;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    public String getArtistText() {
        return artistText;
    }

    public void setArtistText(String artistText) {
        this.artistText = artistText;
    }


    
    
    
    
    
}
