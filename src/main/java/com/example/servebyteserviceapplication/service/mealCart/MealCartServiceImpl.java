package com.example.servebyteserviceapplication.service.mealCart;

import com.example.servebyteserviceapplication.data.dtos.request.CartUpdateDto;
import com.example.servebyteserviceapplication.data.dtos.request.MealCartItemDto;
import com.example.servebyteserviceapplication.data.dtos.response.MealCartResponseDto;
import com.example.servebyteserviceapplication.data.models.Meal;
import com.example.servebyteserviceapplication.data.models.MealCart;
import com.example.servebyteserviceapplication.data.models.MealItem;
import com.example.servebyteserviceapplication.data.models.Restaurant;
import com.example.servebyteserviceapplication.data.repositories.MealCartRepository;
import com.example.servebyteserviceapplication.data.repositories.MealRepository;
import com.example.servebyteserviceapplication.data.repositories.RestaurantRepository;
import com.example.servebyteserviceapplication.web.exceptions.BusinessLogicException;
import com.example.servebyteserviceapplication.web.exceptions.MealDoesNotExistException;
import com.example.servebyteserviceapplication.web.exceptions.RestuarantDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MealCartServiceImpl implements MealCartService {

    @Autowired
    private MealCartRepository mealCartRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MealRepository mealRepository;

    @Override
    public MealCartResponseDto addMealItemToCart(MealCartItemDto mealCartItemDto) {

        Optional<Restaurant> query = restaurantRepository.findById(mealCartItemDto.getRestaurantId());

        if (query.isEmpty()){
            throw new RestuarantDoesNotExistException("Restaurant with id " + mealCartItemDto.getRestaurantId() + " does not exist");
        }

        Restaurant existingRestaurant = query.get();

        MealCart myFavouriteMeal  = existingRestaurant.getMyFavouriteMeal();

        Meal meal = mealRepository.findById(mealCartItemDto.getMealId()).orElse(null);

        if (meal == null){
            throw new MealDoesNotExistException("Meal with id " + mealCartItemDto.getMealId() + " does not exist");
        }
        if (!quantityIsValid(meal, mealCartItemDto.getQuantity())){
            throw new BusinessLogicException("Quantity is too large");
        }

        MealItem mealCartItem = new MealItem(meal , mealCartItemDto.getQuantity());

        myFavouriteMeal.addMeal(mealCartItem);

        myFavouriteMeal.setTotalPrice(myFavouriteMeal.getTotalPrice() + calculateMealItemPrice(mealCartItem));

        mealCartRepository.save(myFavouriteMeal);

        return buildMealCartResponse(myFavouriteMeal);
    }

    private MealCartResponseDto buildMealCartResponse(MealCart myFavouriteMeal) {
        return MealCartResponseDto.builder()
                .mealItems(myFavouriteMeal.getMealList())
                .totalPrice(myFavouriteMeal.getTotalPrice())
                .build();
    }

    private Double calculateMealItemPrice(MealItem mealCartItem) {
        return mealCartItem.getMeal().getMealPrice() * mealCartItem.getQuantityAddedCart();
    }

    private boolean quantityIsValid(Meal meal, int quantity) {
        return meal.getQuantity() >= quantity;
    }

    @Override
    public MealCartResponseDto viewMealCart(Long restaurantId) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);

        if (restaurant == null){
            throw new BusinessLogicException("Restaurant not found");
        }

        MealCart myCart = restaurant.getMyFavouriteMeal();

        return buildMealCartResponse(myCart);
    }

    @Override
    public MealCartResponseDto updateMealCart(CartUpdateDto cartUpdateDto) {
        return null;
    }
}
