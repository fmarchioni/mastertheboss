
package com.mastertheboss.mp.reactive.messaging;

import com.mastertheboss.mp.reactive.messaging.model.WeatherData;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;


@ApplicationScoped
public class TemperatureGenerator {

    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private final List<String> cities = List.of("Paris", "New York", "London", "Tokyo", "Sydney");
    private final Random random = new Random();

    public CompletionStage<WeatherData> getWeatherData() {
        CompletableFuture<WeatherData> future = new CompletableFuture<>();
        ScheduledFuture<?> scheduledFuture = executor.schedule(() -> {
            int temperature = random.nextInt(10) + 10;
            WeatherData weatherData = new WeatherData(getRandomCity(), temperature);
            future.complete(weatherData);
        }, 2, TimeUnit.SECONDS); // Schedule the task to run after 2 seconds

        return future;
    }

    private String getRandomCity() {
        return cities.get(random.nextInt(cities.size()));
    }

}