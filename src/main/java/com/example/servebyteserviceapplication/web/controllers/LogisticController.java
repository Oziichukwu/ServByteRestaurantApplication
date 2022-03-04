package com.example.servebyteserviceapplication.web.controllers;

import com.example.servebyteserviceapplication.data.dtos.ApiResponse;
import com.example.servebyteserviceapplication.data.dtos.request.LogisticDto;
import com.example.servebyteserviceapplication.data.dtos.request.RestaurantRequestDto;
import com.example.servebyteserviceapplication.data.models.City;
import com.example.servebyteserviceapplication.data.models.DeliveryCompany;
import com.example.servebyteserviceapplication.data.models.Restaurant;
import com.example.servebyteserviceapplication.service.logistic.LogisticService;
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
@RequestMapping("/api/logistic")
public class LogisticController {

    @Autowired
    private LogisticService logisticService;


    @GetMapping()
    public ResponseEntity<?> findAllDelivery(){

        List<DeliveryCompany> logisticList = logisticService.getAllDeliveryCompany();
        return new ResponseEntity<>(logisticList, HttpStatus.OK);
    }

    @GetMapping("/{logisticId}")
    public ResponseEntity<?>findDeliveryCompany(@PathVariable Long logisticId){

        try{
            return new ResponseEntity<>(logisticService.findLogisticById(logisticId), HttpStatus.OK);
        }catch (RestuarantDoesNotExistException | BusinessLogicException e){
            return new ResponseEntity<>(new ApiResponse(false, "Restaurant Does not exist"), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> createLogistic(@ModelAttribute LogisticDto requestDto){

        try{
            DeliveryCompany deliveryCompany = logisticService.createLogistic(requestDto);
            return new ResponseEntity<>(deliveryCompany, HttpStatus.CREATED);
        }catch (RestuarantDoesNotExistException | BusinessLogicException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping()
    public ResponseEntity<?>findLogisticByCity(@RequestBody City city){

        try{
            return new ResponseEntity<>(logisticService.findByCityName(city), HttpStatus.OK);
        }catch (BusinessLogicException e){
            return new ResponseEntity<>(new ApiResponse(false,
                    "Restaurant was not found"), HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>updateAllLogisticDetails(@PathVariable Long id, @RequestBody LogisticDto logisticDto){

        try{
            return new ResponseEntity<>(logisticService.updateRestaurant(id, logisticDto), HttpStatus.OK);
        }catch (ServByteServiceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST );
        }
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<?>updateSpecificLogistic(@PathVariable Long id, @RequestBody JsonPatch patch){
        try{
            DeliveryCompany updatedDelivery  = logisticService.updateLogisticDetails(id, patch);
            return ResponseEntity.status(HttpStatus.OK).body(updatedDelivery);
        }catch (ServByteServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

