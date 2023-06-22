package com.mastertheboss.ejb;

import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Startup
@Singleton
public class ReindexingStartupBean {
  
    @Inject
    SearchSession searchSession;
    @PostConstruct
    public void init() throws Exception{
        System.out.println("-----------------------> started!");
        // Trigger the mass reindexing process
        searchSession.massIndexer()
                    .startAndWait();
    }
}
 