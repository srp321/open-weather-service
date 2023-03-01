package com.assignment.openweatherservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherStackResponse {

    @Schema(description = "Request with lookup metadata")
    private Request request;
    @Schema(description = "Location details with geo data")
    private Location location;
    @Schema(description = "Current weather data of the location")
    private Current current;

}
