package com.example.servebyteserviceapplication.service.logistic;

import com.example.servebyteserviceapplication.data.dtos.response.LogisticResponseDto;
import com.example.servebyteserviceapplication.data.models.City;
import com.example.servebyteserviceapplication.data.models.DeliveryCompany;
import com.example.servebyteserviceapplication.data.models.Restaurant;
import com.example.servebyteserviceapplication.data.repositories.DeliveryCompanyRepository;
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
class LogisticServiceImplTest {


    @Autowired
    private DeliveryCompanyRepository deliveryCompanyRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Save a delivery company to the database")
    void saveALogisticCompanyToTheDatabase(){

        DeliveryCompany deliveryCompany = new DeliveryCompany();

        deliveryCompany.setName("Twilight");
        deliveryCompany.setId(4L);
        deliveryCompany.setEmail("twilight13@gmail.com");
        deliveryCompany.setPhoneNumber("8989898989");


        deliveryCompanyRepository.save(deliveryCompany);

        assertThat(deliveryCompany).isNotNull();
        assertThat(deliveryCompany.getId()).isNotNull();

    }


    @Test
    @DisplayName("applying patch to delivery")
    void applyPatchToDeliveryCompany(){

        DeliveryCompany deliveryCompany = new DeliveryCompany();

        deliveryCompany.setName("twinky");
        deliveryCompany.setPhoneNumber("99999999");
        deliveryCompany.setEmail("favour@gmail.com");
        deliveryCompany.setPhoneNumber("99999999");


        deliveryCompanyRepository.save(deliveryCompany);
    }
}