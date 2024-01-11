package com.mastertheboss.flyway;

import org.flywaydb.core.Flyway;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
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