package com.example.dia4.infraestructure.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dia4.domain.Project;


public interface ProjectRepository extends JpaRepository<Project, Long> {

}
