package com.mastertheboss.model;

import com.mastertheboss.ejb.CustomerService;
import jakarta.persistence.*;

import java.util.logging.Logger;

public class CustomerListener {
    private static final Logger logger = Logger.getLogger(CustomerListener.class.getName());

    @PrePersist void onPrePersist(Object o) {
        logger.info("Before persisting "+o);
    }
    @PostPersist void onPostPersist(Object o) {
        logger.info("After persisting "+o);
    }
    @PostLoad void onPostLoad(Object o) {
        logger.info("After Load "+o);
    }
    @PreUpdate void onPreUpdate(Object o) {
        logger.info("Before update "+o);
    }
    @PostUpdate void onPostUpdate(Object o) {
        logger.info("After update "+o);
    }
    @PreRemove void onPreRemove(Object o) {
        logger.info("Before remove "+o);
    }
    @PostRemove
    void onPostRemove(Object o) {
        logger.info("After remove "+o);
    }
}
