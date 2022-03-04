package com.example.servebyteserviceapplication.data.dtos.request;

import com.example.servebyteserviceapplication.data.models.DeliveryChannel;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class LogisticDto {

    private String name;

    private String email;

    private String phoneNumber;

    private MultipartFile logo;

    private DeliveryChannel deliveryChannel;

}
