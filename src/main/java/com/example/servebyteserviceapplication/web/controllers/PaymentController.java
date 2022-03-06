package com.example.servebyteserviceapplication.web.controllers;


import com.example.servebyteserviceapplication.data.dtos.request.PaymentTransactionRequest;
import com.example.servebyteserviceapplication.data.dtos.response.PaymentTransactionResponse;
import com.example.servebyteserviceapplication.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @PostMapping()
    public ResponseEntity<?> makePayment(@RequestBody PaymentTransactionRequest transactionRequest){

        try{
            PaymentTransactionResponse response = paymentService.initializeTransaction(transactionRequest);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (RestClientException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
