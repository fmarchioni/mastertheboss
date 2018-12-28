package com.mastertheboss.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.manager.DefaultCacheManager;
 

@ApplicationScoped
public class ApplicationBean {
  
 
	@Produces
	public RemoteCacheManager defaultRemoteCacheManager() {
		  
		org.infinispan.client.hotrod.configuration.ConfigurationBuilder cb = new org.infinispan.client.hotrod.configuration.ConfigurationBuilder();
		cb.addCluster("remote-cluster").addClusterNode("server-one", 11222);
		RemoteCacheManager rcm = new RemoteCacheManager(cb.build());
		return rcm;
	}
}
