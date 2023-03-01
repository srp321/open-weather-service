package com.assignment.openweatherservice.controller;

import com.assignment.openweatherservice.model.GlobalErrorResponse;
import com.assignment.openweatherservice.model.WeatherStackResponse;
import com.assignment.openweatherservice.service.WeatherService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@OpenAPIDefinition(
        info = @Info(title = "LeasePlan Assignment",
                description = "REST Service to fetch Weather Data")
)
@RestController
@RequestMapping(path = "/v1/weather")
@Slf4j
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(value = "/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Retrieve the Weather Data of a Specific City")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "successfully retrieved weather data",
                    content = @Content(schema = @Schema(implementation = WeatherStackResponse.class))),
            @ApiResponse(responseCode = "404", description = "posts not found",
                    content = @Content(schema = @Schema(implementation = GlobalErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = @Content(schema = @Schema(implementation = GlobalErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "partner server issue",
                    content = @Content(schema = @Schema(implementation = GlobalErrorResponse.class)))
    })
    public WeatherStackResponse getCityWeather(@PathVariable String city) throws Exception {
        log.info("get weather data by city request");
        return weatherService.getWeatherData(city);
    }
}
