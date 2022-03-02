package com.example.servebyteserviceapplication.web.controllers;


import com.example.servebyteserviceapplication.data.dtos.UserDto;
import com.example.servebyteserviceapplication.service.customer.UserService;
import com.example.servebyteserviceapplication.web.exceptions.ServByteServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping()
    public ResponseEntity<?>register(@RequestBody UserDto appUserDto){

        try{
            UserDto responseDto = userService.createUser(appUserDto);
            return ResponseEntity.ok().body(responseDto);
        }catch (ServByteServiceException e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}
