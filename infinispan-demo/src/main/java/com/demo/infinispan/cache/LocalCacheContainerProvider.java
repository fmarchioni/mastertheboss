 
package com.demo.infinispan.cache;
 
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import org.infinispan.commons.api.BasicCacheContainer;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.eviction.EvictionStrategy;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.util.concurrent.IsolationLevel;
 
 
@ApplicationScoped
public class LocalCacheContainerProvider   {
    

    private BasicCacheContainer manager;

    public BasicCacheContainer getCacheContainer() {
        if (manager == null) {
            GlobalConfiguration glob = new GlobalConfigurationBuilder()
                .nonClusteredDefault()  
                .globalJmxStatistics().enable()  
                .build();  
            Configuration loc = new ConfigurationBuilder()
                .jmxStatistics().enable()  
                .clustering().cacheMode(CacheMode.LOCAL)  
                .locking().isolationLevel(IsolationLevel.REPEATABLE_READ)  
                .persistence().passivation(false).addSingleFileStore().purgeOnStartup(true)  
                .build(); //Builds the Configuration object
            manager = new DefaultCacheManager(glob, loc, true);
           System.out.println("Created DefaultCacheManager using library mode");
        }
        return manager;
    }

    @PreDestroy
    public void cleanUp() {
        manager.stop();
        manager = null;
    }
}
