package com.example.dia4.infraestructure.repository;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dia4.applicatin.service.personService;
import com.example.dia4.domain.Rol;
import com.example.dia4.domain.person;

@Service
public class personServiceImpl implements personService{

    private final PersonsRepository personsRepository;

    private final RoleRepository roleRepository;



    public personServiceImpl(PersonsRepository personsRepository, RoleRepository roleRepository) {
        this.personsRepository = personsRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<person> findByUsersFilter(String filter, String value){
        if (filter.toLowerCase().equals("name") && value.isEmpty()) {
            return personsRepository.findByNameContains(value);
        }
        else if(filter.toLowerCase().equals("languaje") && !value.isEmpty()){
            return personsRepository.findByLanguajeEquals(value);
        }
        return personsRepository.findAll();
    }

    @Override
    public List<Rol> finByRolesByFilter(String filter, String values) {
        return roleRepository.findAll();
    }

}
