package com.example.servebyteserviceapplication.data.dtos.request;


import lombok.Data;

@Data
public class MealCartItemDto {

    private Long restaurantId;

    private Long mealId;

    private int quantity;
}
