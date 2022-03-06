package com.example.servebyteserviceapplication.web.controllers;


import com.example.servebyteserviceapplication.data.dtos.request.SmsSenderRequest;
import com.example.servebyteserviceapplication.service.twiloService.TwiloOrderSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/message")
public class SmsController {

    @Autowired
    private TwiloOrderSms twiloOrderSms;


    @PostMapping()
    public void sendSms(@RequestBody SmsSenderRequest senderRequest){
        twiloOrderSms.sendSms(senderRequest);
    }
}
