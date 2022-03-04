package com.example.servebyteserviceapplication.data.dtos.request;

import com.example.servebyteserviceapplication.data.models.Channels;
import com.example.servebyteserviceapplication.data.models.PayStackBearer;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class PaymentTransactionRequest {

    private BigDecimal amount;
    private String email;
    private String reference;
    private String callback_url;
    private Integer invoice_limit;
    private Channels[] channels;
    private String subAccount;
    private Integer transaction_charge;
    private PayStackBearer paystackBearer= PayStackBearer.ACCOUNT;

    public PaymentTransactionRequest(String amount, String email) {
        this.amount = BigDecimal.valueOf(Long.parseLong(amount));
        this.email = email;
    }
}
