package com.example.dia4.infraestructure.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dia4.applicatin.service.personService;
import com.example.dia4.domain.person;
import com.example.dia4.domain.dto.PersonRequest;
import com.example.dia4.domain.dto.PersonResponse;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final personService personService;

    public UserController(com.example.dia4.applicatin.service.personService personService) {
        this.personService = personService;
    }

     @GetMapping("/users")
    public List<PersonResponse> findAll(
        @RequestParam(name = "filter", defaultValue = "") String filter,
        @RequestParam(name = "value", defaultValue =  "")String value
    ){

       List<PersonResponse> results = personService.findByUsersFilter(filter, value);

        return results;
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<PersonResponse> parcialUpdatePerson(@PathVariable Long id, @RequestBody PersonRequest personDto){

        return ResponseEntity.ok().body(personService.patchPerson(id, personDto));
    }


     @DeleteMapping("/users/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable long id) {
        boolean eliminado = personService.eliminarPorId(id);
        if (eliminado) {
            return ResponseEntity.ok("Persona eliminada");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Persona no encontrada");
        }
    }

    @PostMapping("/users")
    public ResponseEntity<PersonResponse> createNewUser(@RequestBody PersonRequest personDto){

        return new ResponseEntity<PersonResponse>(personService.createNewUser(personDto),HttpStatusCode.valueOf(201));
    }
}
