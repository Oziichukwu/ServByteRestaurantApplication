package com.example.servebyteserviceapplication.data.repositories;

import com.example.servebyteserviceapplication.data.models.MealCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealCartRepository extends JpaRepository<MealCart, Long> {

}
