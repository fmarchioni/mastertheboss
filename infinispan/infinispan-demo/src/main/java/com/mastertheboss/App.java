package com.mastertheboss;

import org.infinispan.Cache;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;

public class App
{

    public static void main( String[] args ) throws Exception
    {
        DefaultCacheManager cacheManager = new DefaultCacheManager("config.xml");
        Cache cache = cacheManager.getCache();
        cache.put("key", "value");
        System.out.printf("key = %s\n", cache.get("key"));
        cacheManager.stop();
    }
}