package com.sample.bean;
import java.io.Serializable;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.sample.model.Item;
import com.sample.service.ItemService;

@Named("dtBasicView")
@RequestScoped
public class ItemView implements Serializable {

    private List<Item> items;

    @Inject
    private ItemService service;

    @PostConstruct
    public void init() {
        
        items = service.getItems();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setService(ItemService service) {
        this.service = service;
    }
    
    public void addRandom() {
        service.addRandomItem();
    }
    
    public void onRowEdit(RowEditEvent<Item> event) {
        FacesMessage msg = new FacesMessage("Product Edited", String.valueOf(event.getObject().getName()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
        service.save(event.getObject());
    }

    public void onRowCancel(RowEditEvent<Item> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getName()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}