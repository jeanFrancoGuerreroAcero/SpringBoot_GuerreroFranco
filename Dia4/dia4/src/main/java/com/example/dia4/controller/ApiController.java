package com.example.dia4.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dia4.domain.person;
import com.example.dia4.repository.PersonsRepository;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
// /api/usuarios
public class ApiController {

    private final PersonsRepository personsRepository;

    public ApiController(PersonsRepository personsRepository){
        this.personsRepository = personsRepository;
    }

    @GetMapping("/users")
    public List<person> findAll(){
       List<person> results = personsRepository.findAll();

        return results;
    }


}