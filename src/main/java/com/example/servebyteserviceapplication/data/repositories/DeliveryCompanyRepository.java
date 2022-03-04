package com.example.servebyteserviceapplication.data.repositories;

import com.example.servebyteserviceapplication.data.models.City;
import com.example.servebyteserviceapplication.data.models.DeliveryCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryCompanyRepository extends JpaRepository<DeliveryCompany,Long> {

    List<DeliveryCompany> findByCityName(City city);
    Optional<DeliveryCompany> findByLogisticEmail(String email);

}
