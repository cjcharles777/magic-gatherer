/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightningboltu.magic.gatherer.objects;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Cedric
 */

@Entity
@Cache(region="DeckFormats", usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Table(name = "DeckFormats")
public class DeckFormat implements Serializable
{
    private int id;
    private String name;
    private List<Edition> includedEditions;
    private List<Card> excludedCards;

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    @Column(name = "deck_format_id", nullable=false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", length=250, nullable=false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable(
            name="DeckFormatToEdition",
            joinColumns = @JoinColumn( name="deck_format_id"),
            inverseJoinColumns = @JoinColumn( name="edition_id")
    )
    @BatchSize(size = 20) 
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Edition> getIncludedEditions() {
        return includedEditions;
    }

    public void setIncludedEditions(List<Edition> includedEditions) {
        this.includedEditions = includedEditions;
    }

        @ManyToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable(
            name="DeckFormatToCards",
            joinColumns = @JoinColumn( name="deck_format_id"),
            inverseJoinColumns = @JoinColumn( name="card_edition_id")
    )
    @BatchSize(size = 20) 
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Card> getExcludedCards() {
        return excludedCards;
    }

    public void setExcludedCards(List<Card> excludedCards) {
        this.excludedCards = excludedCards;
    }
    
    
}
