package com.softuni.spring_data_auto_mapping_objects.service.Impl;

import com.softuni.spring_data_auto_mapping_objects.model.dto.UserLoginDto;
import com.softuni.spring_data_auto_mapping_objects.model.dto.UserRegisterDto;
import com.softuni.spring_data_auto_mapping_objects.model.entity.User;
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
    private User loggedInUser;

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
        Set<ConstraintViolation<UserRegisterDto>> violations = validationUtil.getViolations(userRegisterDto);

        if (!violations.isEmpty()) {
            violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }


        User user = modelMapper.map(userRegisterDto, User.class);
        userRepository.save(user);

    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        Set<ConstraintViolation<UserLoginDto>> violations = validationUtil.getViolations(userLoginDto);

        if (!violations.isEmpty()) {
            violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        User user = userRepository.findByEmailAndPassword(userLoginDto.getEmail(),
                                                          userLoginDto.getPassword())
                                  .orElse(null);

        if (user == null) {
            System.out.println("Incorrect username / password");
            return;
        }

        loggedInUser = user;



    }

    @Override
    public void logout() {
        if(loggedInUser == null) {
            System.out.println("Cannot logout. No user was logged in");

        } else {
            loggedInUser = null;
        }
    }
}
