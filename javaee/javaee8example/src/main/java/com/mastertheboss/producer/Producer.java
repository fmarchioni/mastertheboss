package com.mastertheboss.producer;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.mastertheboss.model.SimpleProperty;
import com.mastertheboss.repository.RepositoryManager;

@RequestScoped
public class Producer {
    @Inject
    RepositoryManager db;

    private List<SimpleProperty> propertyList;

    public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final SimpleProperty member) {
        retrieveData();
    }

    @Produces
    @Named
    public List<SimpleProperty> getPropertyList() {
        return propertyList;
    }

    public void setProperty(List<SimpleProperty> property) {
        this.propertyList = propertyList;
    }

    @PostConstruct
    public void retrieveData() {

        propertyList = db.queryCache();

    }

}
