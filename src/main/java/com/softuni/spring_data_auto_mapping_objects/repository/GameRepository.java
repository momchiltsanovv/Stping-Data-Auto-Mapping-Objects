package com.softuni.spring_data_auto_mapping_objects.repository;

import com.softuni.spring_data_auto_mapping_objects.model.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    void removeById(Long id);

    @Query("select g from Game g")
    List<Game> getAllGames();


    Game findByTitle(String title);
}
