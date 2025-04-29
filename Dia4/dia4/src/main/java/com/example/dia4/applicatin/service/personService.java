package com.example.dia4.applicatin.service;

import java.util.List;

import com.example.dia4.domain.Rol;
import com.example.dia4.domain.person;

public interface personService {

    public List<person> findByUsersFilter(String filter, String value);
    public List<Rol> finByRolesByFilter(String filter, String values);
}