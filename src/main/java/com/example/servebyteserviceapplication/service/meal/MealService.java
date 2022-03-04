package com.example.servebyteserviceapplication.service.meal;

import com.example.servebyteserviceapplication.data.dtos.MealDto;
import com.example.servebyteserviceapplication.data.models.Meal;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.List;

public interface MealService {

   Meal findMealById(Long mealId);

   List<Meal>getAllMeals();

   Meal createMeal(MealDto mealDto);

   Meal updateMeal(Long mealId, MealDto mealDto);

   Meal updateMealDetails(Long MealId, JsonPatch patch);
}
