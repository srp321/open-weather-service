package com.assignment.openweatherservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class GlobalErrorResponse {

    @Schema(description = "Error status code")
    private final int status;

    @Schema(description = "Error message with additional information")
    private final String message;
}
