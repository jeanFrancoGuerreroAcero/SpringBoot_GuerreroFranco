package com.example.dia4.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.dia4.applicatin.service.personService;
import com.example.dia4.domain.person;
import com.example.dia4.domain.dto.PersonRequest;

import jakarta.persistence.EntityNotFoundException;


@Service
public class personServiceImpl implements personService{

    private final PersonsRepository personsRepository;

    public personServiceImpl(PersonsRepository personsRepository) {
        this.personsRepository = personsRepository;
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
    public boolean eliminarPorId(Long id) {
        if (personsRepository.existsById(id)) {
            personsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public person patchPerson(Long id, PersonRequest personDto) {
        person person = personsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontro el usuario solicitado"));
    
        if (personDto.getName() != null) {
            person.setName(personDto.getName());
        }
        if (personDto.getSurname() != null) {
            person.setLastName(personDto.getSkill());
        }
        personsRepository.save(person);
        
        return person;
    }

}
