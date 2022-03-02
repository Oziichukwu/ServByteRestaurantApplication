package com.example.servebyteserviceapplication.service.restaurant;

import com.example.servebyteserviceapplication.data.dtos.request.RestaurantRequestDto;
import com.example.servebyteserviceapplication.data.models.City;
import com.example.servebyteserviceapplication.data.models.Restaurant;
import com.example.servebyteserviceapplication.service.restaurant.RestaurantService;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.List;

public class RestaurantServiceImpl implements RestaurantService {


    @Override
    public List<Restaurant> getAllRestaurants() {

        return null;
    }

    @Override
    public Restaurant findRestaurantById(Long restuarantId) {
        return null;
    }

    @Override
    public Restaurant createRestaurant(RestaurantRequestDto restaurantRequestDto) {
        return null;
    }

    @Override
    public Restaurant updateRestaurantDetails(Long restuarantId, JsonPatch patchPath) {
        return null;
    }

    @Override
    public List<Restaurant> findByCityName(City city) {
        return null;
    }
}
