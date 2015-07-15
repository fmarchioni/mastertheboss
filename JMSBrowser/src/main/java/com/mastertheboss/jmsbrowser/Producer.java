/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mastertheboss.jmsbrowser;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;
 

@ApplicationScoped
public class Producer {
@Produces  

   public ConnectionFactory createCF()  {

     ConnectionFactory cf = null;
       
        try {
            cf = (ConnectionFactory) new InitialContext().lookup("java:/ConnectionFactory");
            return cf;
        } catch (NamingException ex) {
            Logger.getLogger(EJBBrowser.class.getName()).log(Level.SEVERE, null, ex);
        }
      return cf;
}
     
}
