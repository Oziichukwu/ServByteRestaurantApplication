package com.example.servebyteserviceapplication.service.payment;

import com.example.servebyteserviceapplication.data.dtos.request.PaymentTransactionRequest;
import com.example.servebyteserviceapplication.data.dtos.response.PaymentTransactionResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service
public class PayStackPaymentServiceImpl implements PaymentService{

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public PaymentTransactionResponse initializeTransaction(PaymentTransactionRequest paymentRequest) throws RestClientException{
        String url = "https://api.paystack.co/transaction/initialize";
        HttpHeaders headers = new HttpHeaders();
        String key = "sk_test_c966b75bbe79b81b2d0ba147dd621561ff534ca3";
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+key);
        HttpEntity<PaymentTransactionRequest> entity =
                new HttpEntity<PaymentTransactionRequest>(paymentRequest, headers);
        try {
            ResponseEntity<PaymentTransactionResponse> response =
                    restTemplate.postForEntity(url, entity, PaymentTransactionResponse.class);
            return response.getBody();
        }catch(RestClientException exception){
            throw exception;
        }
    }
}
