package com.softuni.spring_data_auto_mapping_objects.repository;

import com.softuni.spring_data_auto_mapping_objects.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {



}
