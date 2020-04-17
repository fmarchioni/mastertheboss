package com.mastertheboss.jaxrs;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import java.sql.SQLException;

@ApplicationScoped
public class H2DatabaseStarter {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    // H2 Database
    private Server tcpServer;


    public void observeContextInit(@Observes @Initialized(ApplicationScoped.class) Object event) {
        try {

            tcpServer =  Server.createTcpServer("-tcpPort", "19092", "-tcpAllowOthers").start();
            log.info("H2 database started in TCP server mode on Port 19092");

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }
    }

    void onStart(@Observes StartupEvent ev) {
        log.info("Application is starting");

    }


    void onStop(@Observes ShutdownEvent ev) {

        if (tcpServer != null) {

            tcpServer.stop();
            log.info("H2 database was shut down");
            tcpServer = null;

        }

    }


}