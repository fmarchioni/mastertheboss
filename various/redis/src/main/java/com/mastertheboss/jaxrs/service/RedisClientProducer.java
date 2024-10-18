package com.mastertheboss.jaxrs.service;

import io.lettuce.core.RedisClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class RedisClientProducer {

    // Producer method for RedisClient
    @Produces
    @ApplicationScoped
    public RedisClient createRedisClient() {
        return RedisClient.create("redis://localhost:6379");
    }

    // Disposer method to close the RedisClient
    public void closeRedisClient(@Disposes RedisClient redisClient) {
        redisClient.shutdown();
    }
}