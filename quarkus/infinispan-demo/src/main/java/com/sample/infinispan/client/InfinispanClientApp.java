package com.sample.infinispan.client;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import io.quarkus.infinispan.client.Remote;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.annotation.ClientCacheEntryCreated;
import org.infinispan.client.hotrod.annotation.ClientCacheEntryModified;
import org.infinispan.client.hotrod.annotation.ClientCacheEntryRemoved;
import org.infinispan.client.hotrod.annotation.ClientListener;
import org.infinispan.client.hotrod.event.ClientCacheEntryCreatedEvent;
import org.infinispan.client.hotrod.event.ClientCacheEntryModifiedEvent;
import org.infinispan.client.hotrod.event.ClientCacheEntryRemovedEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class InfinispanClientApp {

    private static final Logger LOGGER = LoggerFactory.getLogger("InfinispanClientApp");

    @Inject
    @Remote("local")
    RemoteCache<String, Customer> cache;


    void onStart(@Observes StartupEvent ev) {

        cache.addClientListener(new EventPrintListener());
        Customer c = new Customer("1","John","Smith");
        cache.put("1", c);
    }

    @ClientListener
    static class EventPrintListener {

        @ClientCacheEntryCreated
        public void handleCreatedEvent(ClientCacheEntryCreatedEvent e) {
            LOGGER.info("Someone has created an entry: " + e);
        }

        @ClientCacheEntryModified
        public void handleModifiedEvent(ClientCacheEntryModifiedEvent e) {
            LOGGER.info("Someone has modified an entry: " + e);
        }

        @ClientCacheEntryRemoved
        public void handleRemovedEvent(ClientCacheEntryRemovedEvent e) {
            LOGGER.info("Someone has removed an entry: " + e);
        }

    }
}
