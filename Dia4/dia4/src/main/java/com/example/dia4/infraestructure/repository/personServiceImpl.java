package com.example.dia4.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.dia4.applicatin.service.personService;
import com.example.dia4.domain.Rol;
import com.example.dia4.domain.person;
import com.example.dia4.infraestructure.error.RolDuplicateException;

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

    @Override
    public Rol createNewRol(String name) {
        Rol newRol = new Rol();
        newRol.setName(name);

        if(getRolByName(name).isPresent()){
            throw new RolDuplicateException("El rol " + name + " ya esta registrado", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return roleRepository.save(newRol);
    }

    private Optional<Rol> getRolByName(String rolName){
        return roleRepository.findByName(rolName);
    }

}
