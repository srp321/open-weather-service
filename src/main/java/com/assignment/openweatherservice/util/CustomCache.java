package com.assignment.openweatherservice.util;

import com.assignment.openweatherservice.client.WeatherServiceClient;
import com.assignment.openweatherservice.model.WeatherStackResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@EnableScheduling
public class CustomCache {

    private WeatherServiceClient weatherServiceClient;

    private Map<String, WeatherStackResponse> weatherDataCache = new HashMap<>();
    private Map<String, Instant> expirationTimes = new HashMap<>();

    public CustomCache(WeatherServiceClient customCache) {
        this.weatherServiceClient = customCache;
    }

    @Scheduled(fixedRate = 60000)
    private void clearOutdatedWeatherData() {
        log.info("scheduler running for cleanup");
        for (String city : weatherDataCache.keySet()) {
            Instant expiration = expirationTimes.get(city);
            if (Instant.now().isAfter(expiration)) {
                removeCityFromCache(city);
            }
        }
    }

    public void addWeatherData(String city, WeatherStackResponse weatherResponse) {
        addWeatherData(city, weatherResponse, Duration.ofMinutes(30));
    }

    public void addWeatherData(String city, WeatherStackResponse weatherResponse, Duration expirationTime) {
        log.info("adding new weather data to cache");
        weatherDataCache.put(city, weatherResponse);
        Instant expiration = Instant.now().plus(expirationTime);
        expirationTimes.put(city, expiration);
    }

    public WeatherStackResponse getWeatherData(String city) throws Exception {
        if (!weatherDataCache.containsKey(city)) {
            addWeatherData(city, weatherServiceClient.getCurrentWeatherData(city));
        }

        Instant expiration = expirationTimes.get(city);
        if (Instant.now().isAfter(expiration)) {
            log.info("refreshing data if found old");
            removeCityFromCache(city);
            addWeatherData(city, weatherServiceClient.getCurrentWeatherData(city));
        }

        return weatherDataCache.get(city);
    }

    public void removeCityFromCache(String city) {
        log.info("Removing old weather data for city: {}", city);
        weatherDataCache.remove(city);
        expirationTimes.remove(city);
    }
}

