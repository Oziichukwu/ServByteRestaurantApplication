package com.example.servebyteserviceapplication.data.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class DeliveryCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String logo;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DeliveryChannel> deliveryChannel;
}
