package com.example.servebyteserviceapplication.data.dtos.response;


import com.example.servebyteserviceapplication.data.models.MealItem;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MealCartResponseDto {

    private List<MealItem> mealItems;
    private double totalPrice;
}
