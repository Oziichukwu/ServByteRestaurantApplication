package com.example.servebyteserviceapplication.service.mealCart;

import com.example.servebyteserviceapplication.data.dtos.request.MealCartItemDto;
import com.example.servebyteserviceapplication.data.models.Meal;
import com.example.servebyteserviceapplication.data.models.MealCart;
import com.example.servebyteserviceapplication.data.models.MealItem;
import com.example.servebyteserviceapplication.data.models.Restaurant;
import com.example.servebyteserviceapplication.data.repositories.MealCartRepository;
import com.example.servebyteserviceapplication.data.repositories.MealRepository;
import com.example.servebyteserviceapplication.data.repositories.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Slf4j
class MealCartServiceImplTest {

    @Autowired
    private MealCartRepository mealCartRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MealRepository mealRepository;


    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Add meal to meal cart test")
    void addMealToMealCartTest(){

        MealCartItemDto mealCartItemDto = new MealCartItemDto();

        mealCartItemDto.setQuantity(1);
        mealCartItemDto.setRestaurantId(80L);
        mealCartItemDto.setMealId(8L);

        Restaurant restaurantInDb = restaurantRepository.findById(mealCartItemDto.getRestaurantId()).orElse(null);
        assertThat(restaurantInDb).isNotNull();

        MealCart mealCart = restaurantInDb.getMyFavouriteMeal();
        assertThat(mealCart).isNotNull();

        Meal meal = mealRepository.findById(12L).orElse(null);
        assertThat(meal).isNotNull();
        assertThat(meal.getQuantity()).isGreaterThanOrEqualTo(mealCartItemDto.getQuantity());

        MealItem mealItem = new MealItem(meal, mealCartItemDto.getQuantity());

        mealCart.addMeal(mealItem);

        mealCartRepository.save(mealCart);
        assertThat(mealCart.getMealList().size()).isEqualTo(1);


    }
}