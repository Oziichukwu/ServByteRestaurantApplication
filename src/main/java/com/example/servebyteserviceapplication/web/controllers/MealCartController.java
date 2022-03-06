package com.example.servebyteserviceapplication.web.controllers;


import com.example.servebyteserviceapplication.data.dtos.ApiResponse;
import com.example.servebyteserviceapplication.data.dtos.request.MealCartItemDto;
import com.example.servebyteserviceapplication.data.dtos.response.MealCartResponseDto;
import com.example.servebyteserviceapplication.service.mealCart.MealCartService;
import com.example.servebyteserviceapplication.web.exceptions.BusinessLogicException;
import com.example.servebyteserviceapplication.web.exceptions.MealDoesNotExistException;
import com.example.servebyteserviceapplication.web.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mealCart")
public class MealCartController {

    @Autowired
    private MealCartService mealCartService;

    @PostMapping("")
    public ResponseEntity<?>addMealToCart(@RequestBody MealCartItemDto mealCartItemDto) {

        try {
            MealCartResponseDto responseDto = mealCartService.addMealItemToCart(mealCartItemDto);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (MealDoesNotExistException | UserNotFoundException | BusinessLogicException e) {
            return new ResponseEntity<>(new ApiResponse(false, "meal added to cart failed"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<?>viewCartItem(@PathVariable Long restaurantId){

        try{
            MealCartResponseDto responseDto = mealCartService.viewMealCart(restaurantId);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }catch (BusinessLogicException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
