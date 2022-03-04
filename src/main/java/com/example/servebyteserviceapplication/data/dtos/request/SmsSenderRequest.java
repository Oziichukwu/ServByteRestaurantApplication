package com.example.servebyteserviceapplication.data.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SmsSenderRequest {

    private final String phoneNumber;
    private final String message;



    public SmsSenderRequest(@JsonProperty("phoneNumber")String phoneNumber, @JsonProperty("message")String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }
}
