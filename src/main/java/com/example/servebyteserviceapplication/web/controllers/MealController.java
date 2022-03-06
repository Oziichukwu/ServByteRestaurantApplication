package com.example.servebyteserviceapplication.web.controllers;


import com.example.servebyteserviceapplication.data.dtos.ApiResponse;
import com.example.servebyteserviceapplication.data.dtos.MealDto;
import com.example.servebyteserviceapplication.data.models.Meal;
import com.example.servebyteserviceapplication.service.meal.MealService;
import com.example.servebyteserviceapplication.web.exceptions.BusinessLogicException;
import com.example.servebyteserviceapplication.web.exceptions.MealDoesNotExistException;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/meal")
public class MealController {

    @Autowired
    private MealService mealService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?>createMeal(@ModelAttribute MealDto mealDto){

        try{
            Meal meal = mealService.createMeal(mealDto);
            return ResponseEntity.status(HttpStatus.OK).body(meal);
        }catch (MealDoesNotExistException | BusinessLogicException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllMeals(){

        List<Meal> meals = mealService.getAllMeals();
        return new ResponseEntity<>(meals, HttpStatus.OK);
    }

    @GetMapping("/{mealId}")
    public ResponseEntity<?>findMeal(@PathVariable Long mealId){

        try{
            return new ResponseEntity<>(mealService.findMealById(mealId),HttpStatus.OK );
        }catch (MealDoesNotExistException | BusinessLogicException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(path = "/{mealId}", consumes = "application/json-patch+json")
    public ResponseEntity<?>updateMeal(@PathVariable Long mealId, @RequestBody JsonPatch patch){

        try{
            Meal updatedMealDetail = mealService.updateMealDetails(mealId, patch);
            return ResponseEntity.status(HttpStatus.OK).body(updatedMealDetail);
        }catch (MealDoesNotExistException | BusinessLogicException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{mealId}")
    public ResponseEntity<?> updateMealDetails(@PathVariable Long mealId, @RequestBody MealDto mealDto){

        try{
            Meal updatedMealDetails = mealService.updateMeal(mealId, mealDto);
            return new ResponseEntity<>(updatedMealDetails, HttpStatus.OK);
        }catch (MealDoesNotExistException | BusinessLogicException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
