package com.example.servebyteserviceapplication.service.meal;


import com.example.servebyteserviceapplication.data.models.City;
import com.example.servebyteserviceapplication.data.models.Meal;
import com.example.servebyteserviceapplication.data.models.Restaurant;
import com.example.servebyteserviceapplication.data.repositories.MealRepository;
import com.example.servebyteserviceapplication.data.repositories.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Slf4j
public class MealServiceImplTest {


    @Autowired
    private MealRepository mealRepository;

    @BeforeEach
    void setUp() {
    }


    @Test
    @DisplayName("Save a meal to the database")
    void saveAMealToTheDatabase(){

        Meal meal = new Meal();

        meal.setName("Twinky tasty");
        meal.setId(4L);
        meal.setMealPreparationTime(LocalTime.of(1,30));
        meal.setMealPrice(800.00);
        meal.setDescription("This is a tasty indomie");

        mealRepository.save(meal);

        assertThat(meal).isNotNull();
        assertThat(meal.getId()).isNotNull();

    }


    @Test
    @DisplayName("applying patch to meal")
    void applyPatchToMeal(){

        Meal meal = new Meal();

        meal.setName("twinky");
        meal.setQuantity(4);
        meal.setDescription("yam porridge");
        meal.setMealPreparationTime(LocalTime.of(1, 15));

        meal.setMealPrice(1000.00);
        mealRepository.save(meal);
    }
}
