package com.assignment.openweatherservice.service.impl;

import com.assignment.openweatherservice.model.WeatherStackResponse;
import com.assignment.openweatherservice.service.WeatherService;
import com.assignment.openweatherservice.util.CustomCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class DefaultWeatherServiceImpl implements WeatherService {

    private CustomCache customCache;

    DefaultWeatherServiceImpl(CustomCache customCache) {
        this.customCache = customCache;
    }

    @Override
    public WeatherStackResponse getWeatherData(String city) throws Exception {
        log.info("In weather service impl");
        return customCache.getWeatherData(city);
    }
}
