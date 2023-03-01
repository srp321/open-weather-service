package com.assignment.openweatherservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    private String name;
    private String country;
    private String region;
    private String lat;
    private String lon;
    @JsonProperty("timezone_id")
    private String timezoneId;
    private String localtime;
    @JsonProperty("localtime_epoch")
    private Long localtimeEpoch;
    @JsonProperty("utc_offset")
    private String utcOffset;

}
