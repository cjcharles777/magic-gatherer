/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightningboltu.magic.gatherer.objects;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author cedric
 */
@Entity
@Table(name = "Edition")
public class Edition implements Serializable
{   
    private String display;
    private String code;
    private int id;

    
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    @Column(name = "edition_id", nullable=false)
    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    
    }
    
    @Column(name = "display", length=100, nullable=false)
    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    @Column(name = "code", length=100, nullable=false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    
}
