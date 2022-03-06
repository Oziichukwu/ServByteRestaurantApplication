package com.example.servebyteserviceapplication.data.dtos.request;

import com.example.servebyteserviceapplication.data.models.DeliveryOptions;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class LogisticDto {

    private String name;

    private String email;

    private String phoneNumber;

    private MultipartFile logo;

    @Enumerated(EnumType.STRING)
    private DeliveryOptions deliveryOptions;

}
