package com.example.servebyteserviceapplication.service.twiloService;


import com.example.servebyteserviceapplication.data.dtos.request.SmsSenderRequest;
import com.example.servebyteserviceapplication.service.twiloConfiguration.TwilioConfiguration;
import com.example.servebyteserviceapplication.web.exceptions.PhoneNumberDoesNotExistException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("twilio")
public class SmsSenderServiceImpl implements TwiloOrderSms{

    private static final Logger LOGGER= LoggerFactory.getLogger(SmsSenderServiceImpl.class);
    private final TwilioConfiguration twiloconfiguration;
    @Autowired
    public SmsSenderServiceImpl(TwilioConfiguration twiloconfiguration) {
        this.twiloconfiguration = twiloconfiguration;
    }

    @Override
    public void sendSms(SmsSenderRequest smsRequest) throws PhoneNumberDoesNotExistException {
        if(isPhoneNumberValid(smsRequest.getPhoneNumber())){
            PhoneNumber to= new PhoneNumber(smsRequest.getPhoneNumber());
            PhoneNumber from= new PhoneNumber(twiloconfiguration.getTrialNumber());
            String message = smsRequest.getMessage();
            MessageCreator createMessage = Message.creator(to, from, message);
            smsRequest.getMessage();

            createMessage.create();
            LOGGER.info("Send sms {}" + smsRequest);
        } else{
            throw new PhoneNumberDoesNotExistException("Phone number [ "+ smsRequest.getPhoneNumber()+ "] does not exist");
        }


    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        return true;
    }

}
