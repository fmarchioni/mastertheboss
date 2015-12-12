/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample;

import org.wildfly.clustering.dispatcher.Command;
import org.wildfly.clustering.group.Node;

public class GarbageCollectorCommand implements Command<String, Node> {

    @Override
    public String execute(Node node) {
        
       System.gc();
            
       return node.getName();
    }
 

}
