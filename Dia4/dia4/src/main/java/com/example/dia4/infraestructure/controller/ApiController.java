package com.example.dia4.infraestructure.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dia4.applicatin.service.personService;
import com.example.dia4.domain.person;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
// /api/usuarios
public class ApiController {

    private final personService personService;

    public ApiController(personService personService){
        this.personService = personService;
    }

    @GetMapping("/users")
    public List<person> findAll(
        @RequestParam(name = "filter", defaultValue = "") String filter,
        @RequestParam(name = "value", defaultValue =  "")String value
    ){

       List<person> results = personService.findByFilter(filter, value);

        return results;
    }


}