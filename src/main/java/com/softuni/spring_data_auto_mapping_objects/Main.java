package com.softuni.spring_data_auto_mapping_objects;

import com.softuni.spring_data_auto_mapping_objects.model.dto.UserRegisterDto;
import com.softuni.spring_data_auto_mapping_objects.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class Main implements CommandLineRunner {

    private final BufferedReader bufferedReader;
    private final UserService userService;


    public Main(UserService userService) {
        this.userService = userService;

        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }


    @Override
    public void run(String... args) throws Exception {
        while (true) {
            System.out.println("Enter command: ");

            String[] commands = bufferedReader.readLine().split("\\|");

            switch (commands[0]) {

                case "RegisterUser" ->  userService
                        .registerUser(new UserRegisterDto(commands[1],
                                                          commands[2],
                                                          commands[3],
                                                          commands[4]));

            }

        }

    }
}
