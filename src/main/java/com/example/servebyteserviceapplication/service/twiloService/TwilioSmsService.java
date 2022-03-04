package com.example.servebyteserviceapplication.service.twiloService;


import com.example.servebyteserviceapplication.data.dtos.request.SmsSenderRequest;
import com.example.servebyteserviceapplication.web.exceptions.PhoneNumberDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("twilio")
public class TwilioSmsService {

    private  SmsSenderServiceImpl twilioSmsSender;

    @Autowired
    public TwilioSmsService(SmsSenderServiceImpl twilioSmsSender) {
        this.twilioSmsSender = twilioSmsSender;
    }

    public void sendSms(SmsSenderRequest smsRequest) throws PhoneNumberDoesNotExistException {
        twilioSmsSender.sendSms(smsRequest);;
    }
}
