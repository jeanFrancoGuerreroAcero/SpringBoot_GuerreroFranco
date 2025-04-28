package com.example.dia4.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.dia4.domain.person;
import java.util.List;


public interface PersonsRepository extends JpaRepository<person, Long> {
    List<person> findByNameContains(String name);
    List<person> findByLanguajeEquals(String name);
}