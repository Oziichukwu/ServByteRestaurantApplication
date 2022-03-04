package com.example.servebyteserviceapplication.service.logistic;


import com.cloudinary.utils.ObjectUtils;
import com.example.servebyteserviceapplication.data.dtos.request.LogisticDto;
import com.example.servebyteserviceapplication.data.dtos.response.LogisticResponseDto;
import com.example.servebyteserviceapplication.data.models.City;
import com.example.servebyteserviceapplication.data.models.DeliveryChannel;
import com.example.servebyteserviceapplication.data.models.DeliveryCompany;
import com.example.servebyteserviceapplication.data.repositories.DeliveryCompanyRepository;
import com.example.servebyteserviceapplication.service.cloud.CloudService;
import com.example.servebyteserviceapplication.web.exceptions.BusinessLogicException;
import com.example.servebyteserviceapplication.web.exceptions.DeliveryCompanyDoesNotExistException;
import com.example.servebyteserviceapplication.web.exceptions.LogisticServiceDoesNotExist;
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
public class LogisticServiceImpl implements LogisticService{

    @Autowired
    private DeliveryCompanyRepository deliveryCompanyRepository;


    @Autowired
    private CloudService cloudService;


    @Override
    public List<DeliveryCompany> getAllDeliveryCompany() {
        return deliveryCompanyRepository.findAll();
    }

    @Override
    public DeliveryCompany findLogisticById(Long id) {

        if(id == null) throw new IllegalArgumentException("Id can not be null");
        Optional<DeliveryCompany> queryResult = deliveryCompanyRepository.findById(id);
        if(queryResult.isPresent()) { return queryResult.get(); }
        throw new DeliveryCompanyDoesNotExistException("Dispatcher with ID: " + id + "does not not exist");
    }

    @Override
    public DeliveryCompany createLogistic(LogisticDto logisticDto) {
        if (logisticDto == null) throw new BusinessLogicException("Argument cannot be null;");
        if (deliveryCompanyRepository.findByLogisticEmail(logisticDto.getEmail()).isPresent()){
            throw new ServByteServiceException("Logistic Company with " + logisticDto.getEmail() + " already exist");
        }

        DeliveryCompany deliveryCompany = new DeliveryCompany();

        try{
            if (logisticDto.getLogo() != null){
                Map<?,?>uploadResult = cloudService.upload(logisticDto.getLogo().getBytes(), ObjectUtils.asMap(
                        "public_id",
                        "inventory/" + logisticDto.getLogo().getOriginalFilename(),
                        "overwrite", true
                ));
                deliveryCompany.setLogo(uploadResult.get("url").toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        deliveryCompany.setName(logisticDto.getName());
        deliveryCompany.setPhoneNumber(logisticDto.getPhoneNumber());
        deliveryCompany.setEmail(logisticDto.getEmail());
        deliveryCompany.setDeliveryChannel((List<DeliveryChannel>) logisticDto.getDeliveryChannel());

        return deliveryCompanyRepository.save(deliveryCompany);
    }

    @Override
    public DeliveryCompany updateRestaurant(Long id, LogisticDto logisticDto) {

        DeliveryCompany updatedDelivery = deliveryCompanyRepository.findById(id).orElseThrow(()->
                new LogisticServiceDoesNotExist("Logistic company does not exist"));

        updatedDelivery.setEmail(logisticDto.getEmail());
        updatedDelivery.setName(logisticDto.getName());
        updatedDelivery.setPhoneNumber(logisticDto.getPhoneNumber());
        updatedDelivery.setDeliveryChannel((List<DeliveryChannel>) logisticDto.getDeliveryChannel());

        return deliveryCompanyRepository.save(updatedDelivery);
    }

    @Override
    public DeliveryCompany updateLogisticDetails(Long id, JsonPatch patch) {

        Optional<DeliveryCompany> productQuery = deliveryCompanyRepository.findById(id);
        if (productQuery.isEmpty()){
            throw new BusinessLogicException("Product with Id" + id + "Does not exist");
        }
        DeliveryCompany updatedProduct = productQuery.get();

        try{
            updatedProduct = applyPatchToProduct(patch , updatedProduct);
            return saveOrUpdateProduct(updatedProduct);
        }catch (JsonPatchException | JsonProcessingException je){
            throw new BusinessLogicException("Product update Failed");
        }
    }
    private DeliveryCompany saveOrUpdateProduct(DeliveryCompany deliveryCompany){
        if (deliveryCompany == null){
            throw new BusinessLogicException("Product cannot be null");
        }
        return deliveryCompanyRepository.save(deliveryCompany);
    }


    private DeliveryCompany applyPatchToProduct(JsonPatch patch, DeliveryCompany updatedProduct) throws JsonPatchException, JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode patched =  patch.apply(objectMapper.convertValue(updatedProduct, JsonNode.class));

        return objectMapper.treeToValue(patched, DeliveryCompany.class);
    }

    @Override
    public List<DeliveryCompany> findByCityName(City city) {
        if(city == null) throw new IllegalArgumentException("Id can not be null");
        List<DeliveryCompany> queryResult = deliveryCompanyRepository.findByCityName(city);
        if(queryResult!=null) return queryResult;
        else throw new DeliveryCompanyDoesNotExistException("Product with ID: does not not exist");
    }
}
