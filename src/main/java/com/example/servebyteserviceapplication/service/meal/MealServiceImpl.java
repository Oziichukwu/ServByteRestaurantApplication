package com.example.servebyteserviceapplication.service.meal;

import com.cloudinary.utils.ObjectUtils;
import com.example.servebyteserviceapplication.data.dtos.MealDto;
import com.example.servebyteserviceapplication.data.models.Meal;
import com.example.servebyteserviceapplication.data.repositories.MealRepository;
import com.example.servebyteserviceapplication.service.cloud.CloudService;
import com.example.servebyteserviceapplication.web.exceptions.BusinessLogicException;
import com.example.servebyteserviceapplication.web.exceptions.MealDoesNotExistException;
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
public class MealServiceImpl implements MealService{

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private CloudService cloudService;


    @Override
    public Meal findMealById(Long mealId) {

        if (mealId == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        Optional<Meal> queryResult = mealRepository.findById(mealId);

        return queryResult.orElseThrow(()->
                new MealDoesNotExistException("Meal with Id " + mealId + " does not exist"));
    }

    @Override
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    @Override
    public Meal createMeal(MealDto mealDto) {

        if (mealDto == null){
            throw new IllegalArgumentException("Argument cannot be null");
        }

        Optional<Meal> query = mealRepository.findByName(mealDto.getName());

        if (query.isPresent()){
            throw new BusinessLogicException("Meal with name " + mealDto.getName() + " does not exist");
        }

        Meal meal = new Meal();


                getMeal(mealDto);
        meal.setName(mealDto.getName());
        meal.setQuantity(mealDto.getQuantity());
        meal.setMealPrice(mealDto.getPrice());
        meal.setDescription(mealDto.getDescription());
        meal.setMealPreparationTime(mealDto.getMealPreparationTime());

        return mealRepository.save(meal);
    }

    private void getMeal(MealDto mealDto) {
        Meal meal = new Meal();

        try{
            if (mealDto.getImage() != null){
                Map<?,?> uploadResult = cloudService.upload(mealDto.getImage().getBytes(), ObjectUtils.asMap(
                        "public_id",
                        "inventory/" + mealDto.getImage().getOriginalFilename(),
                        "overwrite", true
                ));
                meal.setLogoUrl(uploadResult.get("url").toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Meal updateMeal(Long mealId, MealDto mealDto) {
        Meal updatedMeal = mealRepository.findById(mealId).orElseThrow(()->
                new MealDoesNotExistException("Meal with " + mealId + " does not exist"));


        getMeal(mealDto);
        updatedMeal.setMealPrice(mealDto.getPrice());
        updatedMeal.setName(mealDto.getName());
        updatedMeal.setQuantity(mealDto.getQuantity());
        updatedMeal.setMealPreparationTime(mealDto.getMealPreparationTime());
        updatedMeal.setDescription(mealDto.getDescription());

        return mealRepository.save(updatedMeal);
    }

    @Override
    public Meal updateMealDetails(Long mealId, JsonPatch patch) {

        Optional<Meal> mealQuery = mealRepository.findById(mealId);
        if (mealQuery.isEmpty()){
            throw new BusinessLogicException("Product with Id" + mealId + "Does not exist");
        }
        Meal updatedMeal = mealQuery.get();

        try{
            updatedMeal = applyPatchToProduct(patch , updatedMeal);
            return saveOrUpdateMeal(updatedMeal);
        }catch (JsonPatchException | JsonProcessingException je){
            throw new BusinessLogicException("Product update Failed");
        }

    }

    private Meal saveOrUpdateMeal(Meal updatedMeal) {
        if (updatedMeal == null){
            throw new BusinessLogicException("Product cannot be null");
        }
        return mealRepository.save(updatedMeal);
    }

    private Meal applyPatchToProduct(JsonPatch patch, Meal updatedMeal) throws JsonPatchException, JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode patched =  patch.apply(objectMapper.convertValue(updatedMeal, JsonNode.class));

        return objectMapper.treeToValue(patched, Meal.class);
    }
}
