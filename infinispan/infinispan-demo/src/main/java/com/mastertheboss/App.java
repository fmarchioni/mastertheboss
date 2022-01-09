package com.mastertheboss;

import org.infinispan.Cache;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;

public class App 
{
     
    public static void main( String[] args )
    {
          DefaultCacheManager cacheManager = new DefaultCacheManager();
          // Define local cache configuration
          cacheManager.defineConfiguration("local", new ConfigurationBuilder().build());
          // Obtain the local cache
          Cache<String, String> cache = cacheManager.getCache("local");
          // Store a key
          cache.put("name", "john");
          // Retrieve the value for they key and print it out
          System.out.printf("name = %s\n", cache.get("name"));
          // Stop the cache manager and release all resources
          cacheManager.stop();
            
    }
}
