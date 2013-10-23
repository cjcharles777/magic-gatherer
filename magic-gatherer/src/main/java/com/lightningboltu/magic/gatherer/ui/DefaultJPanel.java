/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightningboltu.magic.gatherer.ui;

import org.springframework.context.ApplicationContext;

/**
 *
 * @author cedric
 */
public class DefaultJPanel extends javax.swing.JPanel
{
    private ApplicationContext applicationContext;

    public DefaultJPanel(ApplicationContext applicationContext) 
    {
       super();
       this.applicationContext = applicationContext;
        
    }

    
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    
    
}
