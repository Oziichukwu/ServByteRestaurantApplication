package com.example.servebyteserviceapplication.data.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class MealCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private Double totalPrice;

    @OneToMany
    private List<Meal> meals;

    @OneToMany(cascade = CascadeType.PERSIST, fetch= FetchType.EAGER)
    private List<MealItem> mealList;

    @CreationTimestamp
    private LocalDateTime mealPreparationTime = LocalDateTime.now();


    public void addMeal(MealItem item){
        if(mealList == null){
            mealList = new ArrayList<>();
        }
        mealList.add(item);
    }
}
