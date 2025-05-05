package com.example.dia4.applicatin.service;

import java.util.List;

import com.example.dia4.domain.person;
import com.example.dia4.domain.dto.PersonRequest;

public interface personService {

    public List<person> findByUsersFilter(String filter, String value);
    boolean eliminarPorId(Long id);
    public person  patchPerson(Long id, PersonRequest personDto);

}