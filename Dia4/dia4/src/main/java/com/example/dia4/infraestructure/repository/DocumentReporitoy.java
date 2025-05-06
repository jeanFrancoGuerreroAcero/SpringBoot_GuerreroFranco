package com.example.dia4.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dia4.domain.Passport;

public interface DocumentReporitoy extends JpaRepository<Passport, Long>{
    

}
