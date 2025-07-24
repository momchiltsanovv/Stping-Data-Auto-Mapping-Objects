package com.softuni.spring_data_auto_mapping_objects.service.Impl;

import com.softuni.spring_data_auto_mapping_objects.model.dto.UserRegisterDto;
import com.softuni.spring_data_auto_mapping_objects.repository.UserRepository;
import com.softuni.spring_data_auto_mapping_objects.service.UserService;
import com.softuni.spring_data_auto_mapping_objects.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public UserServiceImpl(UserRepository ur, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = ur;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            System.out.println("Wrong confirm password");
        }
        Set<ConstraintViolation<UserRegisterDto>> violations = validationUtil.violation(userRegisterDto);

        if(!violations.isEmpty()) {
            violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        //todo map dto entity and save in DB

    }
}
