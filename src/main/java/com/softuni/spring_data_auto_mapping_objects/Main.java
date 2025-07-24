package com.softuni.spring_data_auto_mapping_objects;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class Main implements CommandLineRunner {

    private final BufferedReader bufferedReader;

    public Main() {
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }


    @Override
    public void run(String... args) throws Exception {
        while (true) {
            System.out.println("Enter command: ");

            String[] commands = bufferedReader.readLine().split("\\|");

            switch (commands[0]) {

            }

        }

    }
}
