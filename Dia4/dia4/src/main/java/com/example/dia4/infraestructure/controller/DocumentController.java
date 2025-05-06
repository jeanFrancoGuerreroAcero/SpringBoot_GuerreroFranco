package com.example.dia4.infraestructure.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dia4.applicatin.service.personService;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class DocumentController {
    private final personService personService;

    public DocumentController(com.example.dia4.applicatin.service.personService personService) {
        this.personService = personService;
    }

}
