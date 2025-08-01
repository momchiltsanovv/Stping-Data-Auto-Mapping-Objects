package com.softuni.spring_data_auto_mapping_objects.config;

import com.softuni.spring_data_auto_mapping_objects.model.dto.GameAddDto;
import com.softuni.spring_data_auto_mapping_objects.model.entity.Game;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationBeanConfiguration {


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();


        Converter<String, LocalDate> localDateConverter = new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(MappingContext<String, LocalDate> mappingContext) {
                return mappingContext.getSource() == null
                        ? LocalDate.now()
                        : LocalDate.parse(mappingContext.getSource(),
                                          DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            }
        };




        modelMapper.typeMap(GameAddDto.class, Game.class)
                   .addMappings(mapper -> {
                       mapper.map(GameAddDto::getThumbnailURL,
                                  Game::setImageThumbnail);
                       mapper.using(localDateConverter)
                             .map(GameAddDto::getReleaseDate, Game::setReleaseDate);

                   });


        return modelMapper;

    }

}
