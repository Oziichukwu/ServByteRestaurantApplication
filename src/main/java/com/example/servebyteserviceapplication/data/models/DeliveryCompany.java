package com.example.servebyteserviceapplication.data.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
public class DeliveryCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;


    @Enumerated(EnumType.STRING)
    private DeliveryOptions deliveryOptions;

    private String phoneNumber;

    private String logo;

    private LocalTime averageTimeOfDelivery;


        public DeliveryCompany(){
            this.averageTimeOfDelivery = LocalTime.of(1,30);
        }
}
