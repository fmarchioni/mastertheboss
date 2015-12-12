/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample;

 
import javax.annotation.Resource;
 
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.wildfly.clustering.dispatcher.CommandDispatcher;
import org.wildfly.clustering.dispatcher.CommandDispatcherFactory;
import org.wildfly.clustering.group.Group;

@Singleton
@Startup
public class CommandDispatcherFactoryBean implements CommandDispatcherFactory {

    @Resource(lookup = "java:jboss/clustering/dispatcher/web")
    private CommandDispatcherFactory factory;

 
    public <C> CommandDispatcher<C> createCommandDispatcher(Object service, C context) {
        return this.factory.createCommandDispatcher(service, context);
    }

    @Override
    public Group getGroup() {
        return this.factory.getGroup();
    }
}