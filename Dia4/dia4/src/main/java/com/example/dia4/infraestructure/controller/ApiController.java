package com.example.dia4.infraestructure.controller;

import java.util.List;
import java.util.Map;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dia4.applicatin.service.ProjectService;
import com.example.dia4.applicatin.service.personService;
import com.example.dia4.domain.Project;
import com.example.dia4.domain.Rol;
import com.example.dia4.domain.RoleRequest;
import com.example.dia4.domain.person;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
// /api/usuarios
public class ApiController {

    private final personService personService;

    private final ProjectService projectService;

    public ApiController(personService personService, ProjectService projectService) {
        this.personService = personService;
        this.projectService = projectService;
    }

    @GetMapping("/users")
    public List<person> findAll(
        @RequestParam(name = "filter", defaultValue = "") String filter,
        @RequestParam(name = "value", defaultValue =  "")String value
    ){

       List<person> results = personService.findByUsersFilter(filter, value);

        return results;
    }

    
    @GetMapping("/roles")
    public List<Rol> findAllByRoles(
        @RequestParam(name = "filter", defaultValue = "") String filter,
        @RequestParam(name = "value", defaultValue =  "")String value
    ){

       List<Rol> results = personService.finByRolesByFilter(filter, value);

        return results;
    }

    @PostMapping("/roles")
    public ResponseEntity<Rol> newRole(@RequestBody @Valid RoleRequest role){
        return ResponseEntity.ok(personService.createNewRol(role.getName()));
    } 

    @GetMapping("/projects")
    public List<Project> findAllProjects(
        @RequestParam(name = "filter", defaultValue = "")String filter,
        @RequestParam(name = "value", defaultValue =  "")String value
    ){
        List<Project> results = projectService.findAllProjects();

        return results;
    }

}