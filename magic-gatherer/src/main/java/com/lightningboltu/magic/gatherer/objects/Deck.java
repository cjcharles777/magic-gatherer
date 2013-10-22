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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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
@Cache(region="Decks", usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Table(name = "Decks")
public class Deck implements Serializable
{
    private int id;
    private int userId;
    private String name;
    private List<DeckIngredient> ingredients;
    private DeckFormat format;

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    @Column(name = "deck_id", nullable=false)
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

    @OneToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable(
            name="DeckToIngredient",
            joinColumns = @JoinColumn( name="deck_id"),
            inverseJoinColumns = @JoinColumn( name="ingredient_id")
    )
    @BatchSize(size = 20) 
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<DeckIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<DeckIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    public DeckFormat getFormat() {
        return format;
    }

    public void setFormat(DeckFormat format) {
        this.format = format;
    }

      @Column(name = "user_id", nullable=false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    
}
