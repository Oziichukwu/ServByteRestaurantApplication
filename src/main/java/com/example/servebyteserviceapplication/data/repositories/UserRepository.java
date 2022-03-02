package com.example.servebyteserviceapplication.data.repositories;

import com.example.servebyteserviceapplication.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail (String email);
}
