package com.example.servebyteserviceapplication.data.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String logo;

    @Enumerated(EnumType.STRING)
    private City cityName;

    @OneToOne(cascade = CascadeType.ALL)
    private final MealCart myFavouriteMeal;

    public Restaurant(){
        this.myFavouriteMeal = new MealCart();
        this.myFavouriteMeal.setTotalPrice(0.0);
    }

}
