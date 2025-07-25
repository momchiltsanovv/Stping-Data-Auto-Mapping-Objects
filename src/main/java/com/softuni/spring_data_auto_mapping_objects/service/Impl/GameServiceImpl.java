package com.softuni.spring_data_auto_mapping_objects.service.Impl;

import com.softuni.spring_data_auto_mapping_objects.model.dto.GameAddDto;
import com.softuni.spring_data_auto_mapping_objects.model.entity.Game;
import com.softuni.spring_data_auto_mapping_objects.repository.GameRepository;
import com.softuni.spring_data_auto_mapping_objects.service.GameService;
import com.softuni.spring_data_auto_mapping_objects.util.ValidationUtil;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public void addGame(GameAddDto gameAddDto) {
        Set<ConstraintViolation<GameAddDto>> violations = validationUtil.getViolations(gameAddDto);
        if (!violations.isEmpty()) {
            violations.stream()
                      .map(ConstraintViolation::getMessage)
                      .forEach(System.out::println);

            return;
        }

        Game game = modelMapper.map(gameAddDto, Game.class);

        //todo save in DB

        gameRepository.save(game);

        System.out.println("Added game " + gameAddDto.getTitle());
    }

    @Override
    @Transactional
    public void editGame(Long id, BigDecimal price, Double size) {
        Game game = gameRepository.findById(id)
                                  .orElse(null);

        if (game != null) {
            game.setPrice(price);
            game.setSize(size);
        }
    }

    @Override
    @Transactional
    public void deleteGame(Long id) {
        gameRepository.removeById(id);
    }

    @Override
    public void allGames() {
        gameRepository.getAllGames()
                      .stream()
                      .map(g -> String.format("%s %.2f", g.getTitle(), g.getPrice()))
                      .forEach(System.out::println);
    }

    @Override
    public void detailGame(String title) {
        Game g = gameRepository.findByTitle(title);
        String sb = "Title: " + g.getTitle() + System.lineSeparator() +
                "Price: " + g.getPrice() + System.lineSeparator() +
                "Description: " + g.getDescription() + System.lineSeparator() +
                "Release Date: " + g.getReleaseDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + System.lineSeparator() +
                "Size: " + g.getSize() + System.lineSeparator();


        System.out.println(sb);


    }
}
