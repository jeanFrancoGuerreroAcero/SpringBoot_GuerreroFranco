package com.example.dia4.applicatin.service;

import java.util.List;
import com.example.dia4.domain.dto.PersonRequest;
import com.example.dia4.domain.dto.PersonResponse;

public interface personService {

    public List<PersonResponse> findByUsersFilter(String filter, String value);
    boolean eliminarPorId(Long id);
    
    public PersonResponse patchPerson(Long id, PersonRequest personDto);
    public PersonResponse createNewUser(PersonRequest personDto);
}