package com.example.dia4.infraestructure.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.dia4.applicatin.service.RoleService;
import com.example.dia4.domain.Rol;
import com.example.dia4.domain.RoleRequest;


import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
// /api/usuarios
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService ) {
        this.roleService = roleService;
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

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<Rol> eliminarRol(@PathVariable Long id){
        return ResponseEntity.ok().body(roleService.removeRol(id));
    }


}