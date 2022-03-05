package com.example.servebyteserviceapplication.service.customer;

import com.example.servebyteserviceapplication.data.models.User;
import com.example.servebyteserviceapplication.data.repositories.UserRepository;
import com.example.servebyteserviceapplication.data.dtos.UserDto;
import com.example.servebyteserviceapplication.service.customer.UserService;
import com.example.servebyteserviceapplication.service.email.EmailUtil;
import com.example.servebyteserviceapplication.web.exceptions.ServByteServiceException;
import com.example.servebyteserviceapplication.web.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailUtil emailUtil;

    @Override
    public UserDto createUser(UserDto userDto) {

        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new ServByteServiceException("User with email already exist");
        }

       User userDto1 = new User();

        userDto1.setFirstName(userDto.getFirstName());
        userDto1.setLastName(userDto.getLastName());
        userDto1.setEmail(userDto.getEmail());
        userDto1.setAddress(userDto.getAddress());
        userDto1.setPhoneNumber(userDto.getPhoneNumber());

        saveUser(userDto1);

        emailUtil.sendEmail("oziichukwu1@gmail.com", "Location saved","Location saved successfully and about to reture a response");

        return UserDto.builder()
                .firstName(userDto1.getFirstName())
                .lastName(userDto1.getLastName())
                .email(userDto1.getEmail())
                .phoneNumber(userDto1.getPhoneNumber())
                .address(userDto1.getAddress())
                .build();
    }

    private void saveUser(User user) {

        if (user == null){
            throw new UserNotFoundException("User with detail not found");
        }
        userRepository.save(user);
    }
}
