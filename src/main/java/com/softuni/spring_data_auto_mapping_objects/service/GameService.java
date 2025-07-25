package com.softuni.spring_data_auto_mapping_objects.service;

import com.softuni.spring_data_auto_mapping_objects.model.dto.GameAddDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface GameService{
    void addGame(GameAddDto gameAddDto);

    void editGame(Long id, BigDecimal price, Double size);

    void deleteGame(Long l);

    void allGames();

    void detailGame(String command);
}
