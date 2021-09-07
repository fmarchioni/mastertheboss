package infinispan.demoremote;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ClientIntelligence;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.impl.ConfigurationProperties;

public class InfinispanUtil {
	   public static final String USER = "admin";
	   public static final String PASSWORD = "password";

	   public static final String CACHE_NAME = "test";
 

	   public static final String CACHE_CONFIG =
	         "<distributed-cache name=\"CACHE_NAME\">\n"
	         + "    <encoding media-type=\"application/x-protostream\"/>\n"
	         + "</distributed-cache>";


	   public static final ConfigurationBuilder connectionConfig() {
	      ConfigurationBuilder builder = new ConfigurationBuilder();
	      builder.addServer().host("127.0.0.1").port(11222).security()
	            .authentication()
	            .username(USER)
	            .password(PASSWORD);
	        
	      builder.addServer().host("127.0.0.1").port(11322).security()
          .authentication()
          .username(USER)
          .password(PASSWORD);


	      builder.clientIntelligence(ClientIntelligence.BASIC);

	      // Make sure the remote cache is available.
	      // If the cache does not exist, the cache will be created
	      builder.remoteCache(CACHE_NAME)
	            .configuration(CACHE_CONFIG.replace("CACHE_NAME", CACHE_NAME));
	      return builder;
	   }


	   public static final RemoteCacheManager connect() {
	     ConfigurationBuilder builder = connectionConfig();

	      RemoteCacheManager cacheManager = new RemoteCacheManager(builder.build());

	      // Clear the cache in case it already exists from a previous running tutorial
	      cacheManager.getCache(CACHE_NAME).clear();

	      // Return the connected cache manager
	      return cacheManager;
	   }
}
