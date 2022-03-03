package com.example.servebyteserviceapplication.service.restaurant;

import com.example.servebyteserviceapplication.data.dtos.request.RestaurantRequestDto;
import com.example.servebyteserviceapplication.data.models.City;
import com.example.servebyteserviceapplication.data.models.Restaurant;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.List;

public interface RestaurantService {

    List<Restaurant> getAllRestaurants();
    Restaurant findRestaurantById(Long restuarantId);
    Restaurant createRestaurant(RestaurantRequestDto restaurantRequestDto);
    Restaurant updateRestaurantDetails(Long restuarantId, JsonPatch patchPath);
    List<Restaurant> findByCityName(City city);
    Restaurant updateRestaurant(Long restaurantId, RestaurantRequestDto requestDto);
}
