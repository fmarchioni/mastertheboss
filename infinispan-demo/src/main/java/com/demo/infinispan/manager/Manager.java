 
package com.demo.infinispan.manager;

import org.infinispan.commons.api.BasicCache;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import com.demo.infinispan.cache.LocalCacheContainerProvider;
import com.demo.infinispan.model.Item;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
 
import java.util.ArrayList;
 
import java.util.List;

 
@Model
public class Manager {
    public static final String CACHE_NAME = "itemcache";
 

    @Inject
    private LocalCacheContainerProvider cacheProvider;

    private BasicCache<String, Item> cache;

    private String key;
    private String value;
 
    
    public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Manager() {
    }

    public String save() {
        cache = cacheProvider.getCacheContainer().getCache(CACHE_NAME);
        Item item = new Item();
        item.setKey(key);
        item.setValue(value);
        cache.put(item.getKey(), item);
        return "home";
    }

 
 
	public List getCacheList() {
		List<Item> dataList = new ArrayList<Item>();
		if (cache != null)
		dataList.addAll(cache.values());
		
 
		if (cache != null && cache.size() > 0) {
        System.out.println(ClassLayout.parseClass(BasicCache.class).toPrintable(cache));
         
        System.out.println(GraphLayout.parseInstance(cache).toPrintable());
        
        System.out.println(GraphLayout.parseInstance(cache).toFootprint());
		}
		return dataList; 

	}

    public void removeItem(String key) {
        cache = cacheProvider.getCacheContainer().getCache(CACHE_NAME);
        cache.remove(key);
    }

    public void clear() {
        cache = cacheProvider.getCacheContainer().getCache(CACHE_NAME);
        cache.clear();
    }


}
