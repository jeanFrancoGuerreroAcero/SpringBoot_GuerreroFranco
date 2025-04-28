package com.example.dia4.applicatin.service;

import java.util.List;

import com.example.dia4.domain.person;

public interface personService {

    public List<person> findByFilter(String filter, String value);

}