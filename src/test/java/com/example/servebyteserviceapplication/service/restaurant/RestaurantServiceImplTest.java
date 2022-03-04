package com.example.servebyteserviceapplication.service.restaurant;

import com.example.servebyteserviceapplication.data.models.City;
import com.example.servebyteserviceapplication.data.models.Restaurant;
import com.example.servebyteserviceapplication.data.models.User;
import com.example.servebyteserviceapplication.data.repositories.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class RestaurantServiceImplTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    void setUp() {
    }


    @Test
    @DisplayName("Save a restaurant to the database")
    void saveACustomerToTheDatabase(){

        Restaurant restaurant = new Restaurant();

        restaurant.setName("Twinky tasty");
        restaurant.setId(4L);
        restaurant.setEmail("goodnews13@gmail.com");
        restaurant.setCityName(City.ABUJA);

        restaurantRepository.save(restaurant);

        assertThat(restaurant).isNotNull();
        assertThat(restaurant.getId()).isNotNull();

    }


    @Test
    @DisplayName("applying patch to restaurant")
    void applyPatchToRestaurant(){

        Restaurant restaurant = new Restaurant();

        restaurant.setName("twinky");
        restaurant.setPhoneNumber("99999999");
        restaurant.setEmail("favour@gmail.com");
        restaurant.setCityName(City.ABA);

        restaurantRepository.save(restaurant);
    }
}