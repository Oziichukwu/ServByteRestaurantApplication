package com.example.servebyteserviceapplication.service;

import com.example.servebyteserviceapplication.data.models.User;
import com.example.servebyteserviceapplication.data.repositories.UserRepository;
import com.example.servebyteserviceapplication.data.dtos.UserDto;
import com.example.servebyteserviceapplication.web.exceptions.ServByteServiceException;
import com.example.servebyteserviceapplication.web.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new ServByteServiceException("User with email already exist");
        }

       User userDto1 = new User();

        userDto1.setFirstName(userDto.getFirstName());
        userDto1.setLastName(userDto1.getLastName());
        userDto1.setEmail(userDto.getEmail());
        userDto1.setAddress(userDto.getAddress());

        saveUser(userDto1);

        return UserDto.builder()
                .firstName(userDto1.getFirstName())
                .lastName(userDto1.getLastName())
                .email(userDto1.getEmail())
                .phoneNumber(userDto1.getPhoneNumber())
                .build();
    }

    private void saveUser(User user) {

        if (user == null){
            throw new UserNotFoundException("User with detail not found");
        }
        userRepository.save(user);
    }
}
