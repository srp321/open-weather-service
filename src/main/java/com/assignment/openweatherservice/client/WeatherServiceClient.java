package com.assignment.openweatherservice.client;

import com.assignment.openweatherservice.exception.WeatherDataException;
import com.assignment.openweatherservice.model.ErrorResponse;
import com.assignment.openweatherservice.model.WeatherStackResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.assignment.openweatherservice.util.Constants.INVALID_RESPONSE_PARTNER;
import static com.assignment.openweatherservice.util.Constants.PARTNER_ISSUES;

@Slf4j
@Service
public class WeatherServiceClient {

    private RestTemplate restTemplate;

    @Value("${apiKey}")
    private String apiKey;

    @Value("${weatherServiceUrl}")
    private String weatherServiceUrl;

    public WeatherServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherStackResponse getCurrentWeatherData(String city) throws Exception {

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(weatherServiceUrl)
                .queryParam("access_key", apiKey)
                .queryParam("query", city);
        ResponseEntity<String> response
                = restTemplate.getForEntity(builder.toUriString(), String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            if (!(responseBody.contains("success") && responseBody.contains("error"))) {
                log.info("Valid Weather Data Obtained from Partner");
                return objectMapper.readValue(responseBody, WeatherStackResponse.class);
            } else if (responseBody.contains("success") && responseBody.contains("error")) {
                ErrorResponse errorResponse = objectMapper.readValue(responseBody, ErrorResponse.class);
                log.error("Issue while calling partner service: {}", errorResponse.getError().getInfo());
                throw new WeatherDataException(errorResponse.getError().getInfo());
            } else {
                log.error("Issue while parsing partner data: {}", responseBody);
                throw new WeatherDataException(INVALID_RESPONSE_PARTNER);
            }
        } else {
            log.error("Partner responding error: {}", response.getStatusCode());
            throw new WeatherDataException(PARTNER_ISSUES);
        }
    }
}
