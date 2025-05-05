package com.example.dia4.applicatin.service;

import java.util.List;

import com.example.dia4.domain.Rol;

public interface RoleService {
    public List<Rol> finByRolesByFilter(String filter, String values);
    public Rol createNewRol(String name);
    public Rol removeRol(Long id);
   
}
