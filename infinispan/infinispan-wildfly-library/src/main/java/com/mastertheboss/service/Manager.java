package com.mastertheboss.service;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import com.mastertheboss.model.Item;

@Model
public class Manager {

	@Inject  
	DefaultCacheManager cacheManager;

	private String key;
	private String value;

	public Manager() {
	}

	public String save() {
		Cache<String, Item> cache = cacheManager.getCache();
		Item item = new Item();
		item.setKey(key);
		item.setValue(value);
		cache.put(item.getKey(), item);
		return "home";
	}

	public List getCacheList() {
		Cache<String, Item> cache = cacheManager.getCache();
		List list = new ArrayList();
		cache.entrySet().forEach(entry -> list.add(entry.getValue()));
		return list;

	}

	public void removeItem(String key) {
		Cache<String, Item> cache = cacheManager.getCache();   
		cache.remove(key);
	}

	public void clear() {
		Cache<String, Item> cache = cacheManager.getCache();
		cache.clear();
	}
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
}


