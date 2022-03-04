package com.example.servebyteserviceapplication.data.models;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class OrderMeal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    private List<Meal> mealList;
    @OneToMany
    private List<DeliveryCompany> logisticList;
    @OneToMany
    private List<Restaurant> restaurantList;

    @Enumerated(EnumType.STRING)
    private City cityList;
}
