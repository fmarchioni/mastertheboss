package com.sample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sample.model.Item;


@Stateless
public class ItemService {
	List<Item> items;
	
	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void init() {		 
		Query query = em.createQuery("FROM Item");
		items = query.getResultList();
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void addRandomItem() {
		Item item = new Item();
		item.setCategory(UUID.randomUUID().toString());
		item.setName(UUID.randomUUID().toString());
		item.setPrice(Math.random() * 1000 + 1);
		items.add(item);
		em.persist(item);

	}    

	public void save(Item item) {
		Integer id = item.getId();
		Item itemdb = em.find(Item.class, id);
		itemdb.setCategory(item.getCategory());
		itemdb.setName(item.getName());
		itemdb.setPrice(item.getPrice());
		em.persist(itemdb);
		System.out.println("Item persisted: "+item);

	}  
}
