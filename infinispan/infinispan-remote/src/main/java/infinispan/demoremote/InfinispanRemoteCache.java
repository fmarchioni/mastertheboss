package infinispan.demoremote;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;


 
public class InfinispanRemoteCache {

   public static void main(String[] args) {
      // Connect to the server
      RemoteCacheManager cacheManager = InfinispanUtil.connect();
      // Obtain the remote cache
      RemoteCache<String, String> cache = cacheManager.getCache(InfinispanUtil.CACHE_NAME);
      /// Store a value
      cache.put("key", "value");
      // Retrieve the value and print it out
      System.out.printf("key = %s\n", cache.get("key"));
      // Stop the cache manager and release all resources
      cacheManager.stop();
   }

}
