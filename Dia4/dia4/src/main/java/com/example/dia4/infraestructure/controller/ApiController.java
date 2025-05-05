package com.example.dia4.infraestructure.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dia4.applicatin.service.ProjectService;
import com.example.dia4.applicatin.service.RoleService;
import com.example.dia4.applicatin.service.personService;
import com.example.dia4.domain.Project;
import com.example.dia4.domain.Rol;
import com.example.dia4.domain.RoleRequest;
import com.example.dia4.domain.person;
import com.example.dia4.domain.dto.PersonRequest;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
// /api/usuarios
public class ApiController {

    private final personService personService;

    private final ProjectService projectService;

    private final RoleService roleService;

    public ApiController(personService personService,RoleService roleService ,ProjectService projectService) {
        this.personService = personService;
        this.projectService = projectService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public List<person> findAll(
        @RequestParam(name = "filter", defaultValue = "") String filter,
        @RequestParam(name = "value", defaultValue =  "")String value
    ){

       List<person> results = personService.findByUsersFilter(filter, value);

        return results;
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<person> parcialUpdatePerson(@PathVariable Long id, @RequestBody PersonRequest personDto){

        return ResponseEntity.ok().body(personService.patchPerson(id, personDto));
    }


    @GetMapping("/roles")
    public List<Rol> findAllByRoles(
        @RequestParam(name = "filter", defaultValue = "") String filter,
        @RequestParam(name = "value", defaultValue =  "")String value
    ){

       List<Rol> results = roleService.finByRolesByFilter(filter, value);

        return results;
    }

    @PostMapping("/roles")
    public ResponseEntity<Rol> newRole(@RequestBody @Valid RoleRequest role){
        return ResponseEntity.ok(roleService.createNewRol(role.getName()));
    } 

    @GetMapping("/projects")
    public List<Project> findAllProjects(
        @RequestParam(name = "filter", defaultValue = "")String filter,
        @RequestParam(name = "value", defaultValue =  "")String value
    ){
        List<Project> results = projectService.findAllProjects();

        return results;
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

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<Rol> eliminarRol(@PathVariable Long id){
        return ResponseEntity.ok().body(roleService.removeRol(id));
    }



}