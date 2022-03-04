package com.example.servebyteserviceapplication.service.user;

import com.example.servebyteserviceapplication.data.models.User;
import com.example.servebyteserviceapplication.data.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Slf4j
@SpringBootTest
//@Sql(scripts = {"/db/data.sql"})
class UserServiceImplTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Autowired
    private UserRepository userRepository;


    @Test
    @DisplayName("Save a user to the database")
    void saveACustomerToTheDatabase(){

        User user = new User();
        user.setFirstName("micheal");
        user.setLastName("favour");
        user.setEmail("favour@gmail.com");
        user.setPhoneNumber("9999999");
        user.setAddress("78, femi street");

        userRepository.save(user);

        assertThat(user).isNotNull();
        assertThat(user.getId()).isNotNull();
    }

    @Test
    @DisplayName("Already existing user in the database can be located")
    void findExistingUserInTheDatabaseTest(){

        User user = userRepository.findById(2L).orElse(null);

        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(2L);
        assertThat(user.getFirstName()).isEqualTo("bisi");
        assertThat(user.getLastName()).isEqualTo("bolaji");

        log.info("User retrieved -> {}", user);
    }

}