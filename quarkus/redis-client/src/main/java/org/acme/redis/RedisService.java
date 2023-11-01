package org.acme.redis;

import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.keys.ReactiveKeyCommands;
import io.quarkus.redis.datasource.string.StringCommands;
import io.smallrye.mutiny.Uni;

import java.util.List;

import jakarta.inject.Singleton;

@Singleton
class RedisService {

    private ReactiveKeyCommands<String> keys;
    private StringCommands<String, Integer> cmd;
    private RedisDataSource redisDS;
    public RedisService(RedisDataSource redisDS,  ReactiveRedisDataSource reactiveRedisDS) {
        this.redisDS = redisDS;
        keys = reactiveRedisDS.key();
        cmd = redisDS.string(Integer.class);
    }


    Uni<Void> del(String key) {
        return keys.del(key)
            .replaceWithVoid();
            
    }

    int get(String key) {
        return cmd.get(key);
    }

    void set(Data data) {

        cmd.set(data.key(), data.value());
    }

    void increment(String key, int incrementBy) {
        cmd.incrby(key, incrementBy);
    }

    String execute(String command, String param ) {
        return redisDS.execute(command,param).toString();
    }

    Uni<List<String>> keys() {
        return keys
                .keys("*");
    }
}

