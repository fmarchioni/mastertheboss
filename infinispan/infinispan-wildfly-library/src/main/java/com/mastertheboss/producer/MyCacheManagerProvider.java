package com.mastertheboss.producer;


import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.cache.CacheMode;

@ApplicationScoped
public class MyCacheManagerProvider {

	private static final long ENTRY_LIFESPAN = 60 * 1000; // 60 seconds


	private DefaultCacheManager manager;

	public DefaultCacheManager getCacheManager() {
		if (manager == null) {

			GlobalConfiguration glob = new GlobalConfigurationBuilder().clusteredDefault() 
					.transport().addProperty("configurationFile", "jgroups-udp.xml")  
					.globalJmxStatistics().allowDuplicateDomains(true).enable() 
					.build();  
			Configuration loc = new ConfigurationBuilder().jmxStatistics().enable()  
					.clustering().cacheMode(CacheMode.DIST_SYNC)  
					.hash().numOwners(2)  
					.expiration().lifespan(ENTRY_LIFESPAN)  
					.build();
			manager = new DefaultCacheManager(glob, loc, true);
		}
		return manager;
	}

	@PreDestroy
	public void cleanUp() {
		manager.stop();
		manager = null;
	}

}
