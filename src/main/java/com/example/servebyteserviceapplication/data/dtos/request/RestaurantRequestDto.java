package com.example.servebyteserviceapplication.data.dtos.request;


import com.example.servebyteserviceapplication.data.models.City;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RestaurantRequestDto {

    private String name;
    private String email;
    private String phoneNumber;
    private City city;
    private MultipartFile logo;
}
