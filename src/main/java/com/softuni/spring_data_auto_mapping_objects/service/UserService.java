package com.softuni.spring_data_auto_mapping_objects.service;

import com.softuni.spring_data_auto_mapping_objects.model.dto.UserLoginDto;
import com.softuni.spring_data_auto_mapping_objects.model.dto.UserRegisterDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);

    void loginUser(UserLoginDto userLoginDto);

    void logout();
}
