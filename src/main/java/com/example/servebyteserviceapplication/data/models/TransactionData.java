package com.example.servebyteserviceapplication.data.models;

import lombok.Data;

@Data
public class TransactionData {

    private String authorization_url;
    private String access_code;
    private String reference;
}
