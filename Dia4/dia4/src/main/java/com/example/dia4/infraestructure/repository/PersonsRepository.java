package com.example.dia4.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.dia4.domain.person;
import java.util.List;
import java.util.Optional;


public interface PersonsRepository extends JpaRepository<person, Long> {
    List<person> findByNameContains(String name);
    List<person> findByLanguajeEquals(String name);
    Optional<person> findPersonByPassportNumber(String number);
}