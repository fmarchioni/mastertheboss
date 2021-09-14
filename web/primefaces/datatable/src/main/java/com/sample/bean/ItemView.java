package com.sample.bean;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.sample.model.Item;
import com.sample.service.ItemService;

@Named("dtBasicView")
@ViewScoped
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