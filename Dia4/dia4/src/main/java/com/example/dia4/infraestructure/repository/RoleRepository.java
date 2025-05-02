package com.example.dia4.infraestructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dia4.domain.Rol;

public interface RoleRepository extends JpaRepository<Rol, Long>{

    Optional<Rol> findByName(String name);

}