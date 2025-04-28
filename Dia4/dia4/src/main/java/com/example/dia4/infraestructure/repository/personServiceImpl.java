package com.example.dia4.infraestructure.repository;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dia4.applicatin.service.personService;
import com.example.dia4.domain.person;

@Service
public class personServiceImpl implements personService{

    private final PersonsRepository personsRepository;

    public personServiceImpl(PersonsRepository personsRepository) {
        this.personsRepository = personsRepository;
    }

    @Override
    public List<person> findByFilter(String filter, String value){
        if (filter.toLowerCase().equals("name") && value.isEmpty()) {
            return personsRepository.findByNameContains(value);
        }
        else if(filter.toLowerCase().equals("languaje") && !value.isEmpty()){
            return personsRepository.findByLanguajeEquals(value);
        }
        return personsRepository.findAll();
    }

}
