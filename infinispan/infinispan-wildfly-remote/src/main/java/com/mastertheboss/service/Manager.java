package com.mastertheboss.service;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.infinispan.Cache;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.manager.DefaultCacheManager;
import com.mastertheboss.model.Item;

@Model
public class Manager {


	@Inject  
	RemoteCacheManager cacheManager;


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
		RemoteCache<String, Object> cache = cacheManager.getCache("default");
		Item item = new Item();
		item.setKey(key);
		item.setValue(value);
		cache.put(item.getKey(), item);
		return "home";
	}

	public List getCacheList() {
		RemoteCache<String, Object> cache = cacheManager.getCache("default");
		List list = new ArrayList();
		cache.entrySet().forEach(entry -> list.add(entry.getValue()));
		return list;

	}

	public void removeItem(String key) {
		RemoteCache<String, Object> cache = cacheManager.getCache("default");  
		cache.remove(key);
	}

	public void clear() {
		RemoteCache<String, Object> cache = cacheManager.getCache("default");
		cache.clear();
	}

}


