package com.example.servebyteserviceapplication.data.models;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phoneNumber;


    @Column(length = 500)
    private String address;
}
