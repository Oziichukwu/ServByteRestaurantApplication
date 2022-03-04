package com.example.servebyteserviceapplication.service.mealCart;

import com.example.servebyteserviceapplication.data.dtos.request.CartUpdateDto;
import com.example.servebyteserviceapplication.data.dtos.request.MealCartItemDto;
import com.example.servebyteserviceapplication.data.dtos.response.MealCartResponseDto;

public interface MealCartService {

    MealCartResponseDto addMealItemToCart(MealCartItemDto mealCartItemDto);

    MealCartResponseDto viewMealCart(Long restaurantId);

    MealCartResponseDto updateMealCart(CartUpdateDto cartUpdateDto);

}
