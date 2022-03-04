package com.example.servebyteserviceapplication.data.repositories;

import com.example.servebyteserviceapplication.data.models.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Long> {

    boolean existsByName(String name);

    Optional<Meal> findByName(String name);
}
