package com.example.servebyteserviceapplication.web.controllers;

import com.example.servebyteserviceapplication.data.dtos.ApiResponse;
import com.example.servebyteserviceapplication.data.dtos.request.RestaurantRequestDto;
import com.example.servebyteserviceapplication.data.models.City;
import com.example.servebyteserviceapplication.data.models.Restaurant;
import com.example.servebyteserviceapplication.service.restaurant.RestaurantService;
import com.example.servebyteserviceapplication.web.exceptions.BusinessLogicException;
import com.example.servebyteserviceapplication.web.exceptions.RestuarantDoesNotExistException;
import com.example.servebyteserviceapplication.web.exceptions.ServByteServiceException;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping()
    public ResponseEntity<?> findAllRestaurant(){

        List<Restaurant> restaurantList = restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurantList, HttpStatus.OK);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<?>findRestaurant(@PathVariable Long restaurantId){

        try{
            return new ResponseEntity<>(restaurantService.findRestaurantById(restaurantId), HttpStatus.OK);
        }catch (RestuarantDoesNotExistException | BusinessLogicException e){
            return new ResponseEntity<>(new ApiResponse(false, "Restaurant Does not exist"), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> createRestaurant(@ModelAttribute RestaurantRequestDto requestDto){

        try{
            Restaurant restaurant = restaurantService.createRestaurant(requestDto);
            return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
        }catch (RestuarantDoesNotExistException | BusinessLogicException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<?>findRestaurantByCity(@RequestBody City city){

        try{
            return new ResponseEntity<>(restaurantService.findByCityName(city), HttpStatus.OK);
        }catch (BusinessLogicException e){
            return new ResponseEntity<>(new ApiResponse(false,
                    "Restaurant was not found"), HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<?>updateAllRestaurantDetails(@PathVariable Long restaurantId, @RequestBody RestaurantRequestDto requestDto){

        try{
            return new ResponseEntity<>(restaurantService.updateRestaurant(restaurantId, requestDto), HttpStatus.OK);
        }catch (ServByteServiceException  e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST );
        }
    }

    @PatchMapping(path = "/{restaurantId}", consumes = "application/json-patch+json")
    public ResponseEntity<?>updateSpecificRestaurant(@PathVariable Long restaurantId, @RequestBody JsonPatch patch){
        try{
            Restaurant updatedRestaurant  = restaurantService.updateRestaurantDetails(restaurantId, patch);
            return ResponseEntity.status(HttpStatus.OK).body(updatedRestaurant);
        }catch (ServByteServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
