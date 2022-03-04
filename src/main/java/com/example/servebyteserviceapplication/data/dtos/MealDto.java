package com.example.servebyteserviceapplication.data.dtos;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class MealDto {

    private String name;

    private double price;

    private int quantity;

    private String description;

    private MultipartFile image;

    @CreationTimestamp
    private LocalTime mealPreparationTime;

    public MealDto(){
        this.mealPreparationTime = LocalTime.of(1, 15);
    }
}
