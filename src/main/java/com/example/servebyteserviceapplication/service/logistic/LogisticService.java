package com.example.servebyteserviceapplication.service.logistic;

import com.example.servebyteserviceapplication.data.dtos.request.LogisticDto;
import com.example.servebyteserviceapplication.data.dtos.response.LogisticResponseDto;
import com.example.servebyteserviceapplication.data.models.City;
import com.example.servebyteserviceapplication.data.models.DeliveryCompany;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.List;

public interface LogisticService {

    List<DeliveryCompany> getAllDeliveryCompany();

    DeliveryCompany findLogisticById(Long id);

    DeliveryCompany createLogistic(LogisticDto logisticDto);

    DeliveryCompany updateRestaurant(Long id, LogisticDto logisticDto);

    DeliveryCompany updateLogisticDetails(Long id, JsonPatch patch);


}
