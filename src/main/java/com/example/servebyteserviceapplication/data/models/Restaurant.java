package com.example.servebyteserviceapplication.data.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class RestaurantOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String logo;

    @Enumerated(EnumType.STRING)
    private City cityName;
}
