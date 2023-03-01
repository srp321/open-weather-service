package com.assignment.openweatherservice.service;

import com.assignment.openweatherservice.model.WeatherStackResponse;

public interface WeatherService {
    WeatherStackResponse getWeatherData(String city) throws Exception;
}
