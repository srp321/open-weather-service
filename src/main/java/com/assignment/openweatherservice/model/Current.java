package com.assignment.openweatherservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Current {

    @JsonProperty("observation_time")
    private String observationTime;
    private double temperature;
    @JsonProperty("weather_code")
    private int weatherCode;
    @JsonProperty("weather_icons")
    private String[] weatherIcons;
    @JsonProperty("weather_descriptions")
    private String[] weatherDescriptions;
    @JsonProperty("wind_speed")
    private int windSpeed;
    @JsonProperty("wind_degree")
    private int windDegree;
    @JsonProperty("wind_dir")
    private String windDir;
    private int pressure;
    private int precip;
    private int humidity;
    private int cloudcover;
    private int feelslike;
    @JsonProperty("uv_index")
    private int uvIndex;
    private int visibility;
    @JsonProperty("is_day")
    private String isDay;

}
