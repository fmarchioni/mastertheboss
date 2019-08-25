package com.mastertheboss.flyway;

import org.flywaydb.core.Flyway;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;

@Singleton
@Startup
@TransactionManagement(TransactionManagementType.BEAN)
public class MigrationEJB {

    @Resource(lookup = "java:/PostGreDS")
    private DataSource dataSource;

    @PostConstruct
    public void initFlyWay() {
        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.clean();
        flyway.baseline();
        flyway.migrate();
    }
}