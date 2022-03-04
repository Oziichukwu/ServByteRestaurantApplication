package com.example.servebyteserviceapplication.data.models;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Double mealPrice;

    private int quantity;

    private String logoUrl;

    private String description;

//    @ManyToOne
//    @JoinColumn(name = "restaurant_id")
//    private  Restaurant restaurant;

    @CreationTimestamp
    private LocalTime mealPreparationTime;

    public Meal(){
        this.mealPreparationTime = LocalTime.of(1, 15);
    }
}
