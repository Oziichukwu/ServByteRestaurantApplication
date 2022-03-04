package com.example.servebyteserviceapplication.service.restaurant;

import com.cloudinary.utils.ObjectUtils;
import com.example.servebyteserviceapplication.data.dtos.request.RestaurantRequestDto;
import com.example.servebyteserviceapplication.data.models.City;
import com.example.servebyteserviceapplication.data.models.Restaurant;
import com.example.servebyteserviceapplication.data.repositories.RestaurantRepository;
import com.example.servebyteserviceapplication.service.cloud.CloudService;
import com.example.servebyteserviceapplication.web.exceptions.BusinessLogicException;
import com.example.servebyteserviceapplication.web.exceptions.RestuarantDoesNotExistException;
import com.example.servebyteserviceapplication.web.exceptions.ServByteServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CloudService cloudService;



    @Override
    public List<Restaurant> getAllRestaurants() {

        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant findRestaurantById(Long restaurantId) {
        if (restaurantId == null) throw new IllegalArgumentException("Kindly enter a valid id");
        Optional<Restaurant> queryResult = restaurantRepository.findById(restaurantId);

        if (queryResult.isPresent()) {
            return queryResult.get();
        }
        throw new RestuarantDoesNotExistException("Restaurant does not exist");
    }

    @Override
    public Restaurant createRestaurant(RestaurantRequestDto restaurantRequestDto) {

        if (restaurantRequestDto == null) throw new ServByteServiceException("Restaurant request cannot be null");

        Optional<Restaurant> query = restaurantRepository.findByRestaurantEmail(restaurantRequestDto.getEmail());

        if (query.isPresent()){
            throw new ServByteServiceException("Restaurant with email " + restaurantRequestDto.getEmail()+ " does not exist");
        }

        Restaurant restaurant = new Restaurant();

                getRestaurant(restaurantRequestDto);
        restaurant.setName(restaurantRequestDto.getName());
        restaurant.setEmail(restaurantRequestDto.getEmail());
        restaurant.setPhoneNumber(restaurantRequestDto.getPhoneNumber());
        restaurant.setCityName(City.valueOf(restaurantRequestDto.getCity().toString()));

        return restaurantRepository.save(restaurant);

    }


    public void getRestaurant(RestaurantRequestDto restaurantRequestDto) {

        Restaurant restaurant = new Restaurant();

        try {
            if (restaurantRequestDto.getLogo() != null){
                Map<?,?> uploadResult = cloudService.upload(restaurantRequestDto.getLogo().getBytes(), ObjectUtils.asMap(
                        "public_id",
                        "inventory/" + restaurantRequestDto.getLogo().getOriginalFilename(),
                        "overwrite", true
                ));
                restaurant.setLogo(uploadResult.get("url").toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Restaurant updateRestaurantDetails(Long restaurantId, JsonPatch patchPath) {

        Optional<Restaurant> restaurantQuery = restaurantRepository.findById(restaurantId);
        if (restaurantQuery.isEmpty()){
            throw new BusinessLogicException("Restaurant with id " + restaurantId + " does not exist");
        }

        Restaurant updatedRestaurant = restaurantQuery.get();

        try{
            updatedRestaurant = applyPatchToRestaurant(patchPath , updatedRestaurant);
            return saveOrUpdateProduct(updatedRestaurant);
        }catch (JsonPatchException | JsonProcessingException je){
            throw new BusinessLogicException("Restaurant update failed");
        }
    }

    private Restaurant saveOrUpdateProduct(Restaurant updatedRestaurant) {

        if (updatedRestaurant == null){
            throw new BusinessLogicException("Restaurant cannot be null");
        }

        return restaurantRepository.save(updatedRestaurant);

    }


    private Restaurant applyPatchToRestaurant(JsonPatch patchPath, Restaurant updatedRestaurant) throws JsonPatchException, JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode patched = patchPath.apply(objectMapper.convertValue(updatedRestaurant, JsonNode.class));

        return objectMapper.treeToValue(patched, Restaurant.class);
    }

    @Override
    public List<Restaurant> findByCityName(City city) {

        if (city == null){
            throw new IllegalArgumentException("kindly enter a valid city name");
        }
        List<Restaurant> restaurantByCity = restaurantRepository.findByCityName(city);
        if (restaurantByCity != null) return restaurantByCity;
        else throw new RestuarantDoesNotExistException("Restuarant with name does not exist");
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, RestaurantRequestDto requestDto) {

        Restaurant updatedRestaurant = restaurantRepository.findById(restaurantId).orElseThrow(()->
                new RestuarantDoesNotExistException("Restaurant with " + restaurantId + " does not exist"));

                getRestaurant(requestDto);
        updatedRestaurant.setName(requestDto.getName());
        updatedRestaurant.setEmail(requestDto.getEmail());
        updatedRestaurant.setPhoneNumber(requestDto.getPhoneNumber());
        updatedRestaurant.setCityName(City.valueOf(requestDto.getCity().toString()));

        return restaurantRepository.save(updatedRestaurant);
    }
}
