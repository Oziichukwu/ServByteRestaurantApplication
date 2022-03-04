package com.example.servebyteserviceapplication.service.payment;

import com.example.servebyteserviceapplication.data.dtos.request.PaymentTransactionRequest;
import com.example.servebyteserviceapplication.data.dtos.response.PaymentTransactionResponse;

public interface PaymentService {

    PaymentTransactionResponse initializeTransaction(PaymentTransactionRequest paymentRequest);
}
