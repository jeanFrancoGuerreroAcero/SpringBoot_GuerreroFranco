package com.example.dia4.infraestructure.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dia4.applicatin.service.ProjectService;
import com.example.dia4.applicatin.service.personService;
import com.example.dia4.domain.Project;
import com.example.dia4.domain.Rol;
import com.example.dia4.domain.person;

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

    @GetMapping("/projects")
    public List<Project> findAllProjects(
        @RequestParam(name = "filter", defaultValue = "")String filter,
        @RequestParam(name = "value", defaultValue =  "")String value
    ){
        List<Project> results = projectService.findAllProjects();

        return results;
    }
    


}