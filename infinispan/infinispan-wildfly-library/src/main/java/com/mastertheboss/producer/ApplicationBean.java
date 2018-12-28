package com.mastertheboss.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import org.infinispan.manager.DefaultCacheManager;
 

@ApplicationScoped
public class ApplicationBean {
  
 
    @Inject
    MyCacheManagerProvider cacheManagerProvider;
 

    @Produces
    DefaultCacheManager getDefaultCacheManager() {
        return cacheManagerProvider.getCacheManager();
    }
}
