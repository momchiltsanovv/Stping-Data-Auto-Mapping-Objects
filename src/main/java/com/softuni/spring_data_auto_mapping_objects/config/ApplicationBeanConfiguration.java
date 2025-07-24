package com.softuni.spring_data_auto_mapping_objects.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
