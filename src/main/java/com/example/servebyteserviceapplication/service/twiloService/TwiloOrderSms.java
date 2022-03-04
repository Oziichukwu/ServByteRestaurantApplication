package com.example.servebyteserviceapplication.service.twiloService;

import com.example.servebyteserviceapplication.data.dtos.request.SmsSenderRequest;

public interface TwiloOrderSms {

    void sendSms(SmsSenderRequest smsRequest);
}
