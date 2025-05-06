package com.example.dia4.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.dia4.applicatin.service.personService;
import com.example.dia4.domain.Passport;
import com.example.dia4.domain.Rol;
import com.example.dia4.domain.person;
import com.example.dia4.domain.dto.PersonRequest;
import com.example.dia4.domain.dto.PersonResponse;
import com.example.dia4.infraestructure.error.RolDuplicateException;

import jakarta.persistence.EntityNotFoundException;


@Service
public class personServiceImpl implements personService{

    private final PersonsRepository personsRepository;
    private final RoleRepository roleRepository;
    private final DocumentReporitoy documentReporitoy;


    public personServiceImpl(PersonsRepository personsRepository, RoleRepository roleRepository, DocumentReporitoy documentReporitoy) {
        this.personsRepository = personsRepository;
        this.roleRepository = roleRepository;
        this.documentReporitoy = documentReporitoy;
    }

    @Override
    public List<PersonResponse> findByUsersFilter(String filter, String value){
        if (filter.toLowerCase().equals("name") && value.isEmpty()) {
            return personsRepository.findAll().stream().map((person) -> {
                PersonResponse response = new PersonResponse();
                response.setName(person.getName());
                response.setSurname(person.getLastName());
                response.setSkill(person.getLanguaje());
                response.setPassport(person.getPassport() != null);
                return response;
                }).toList();
        }
        else if(filter.toLowerCase().equals("languaje") && !value.isEmpty()){
            return personsRepository.findAll().stream().map((person) -> {
                PersonResponse response = new PersonResponse();
                response.setName(person.getName());
                response.setSurname(person.getLastName());
                response.setSkill(person.getLanguaje());
                response.setPassport(person.getPassport() != null);
                return response;
                }).toList();
        }
        return personsRepository.findAll().stream().map((person) -> {
        PersonResponse response = new PersonResponse();
        response.setName(person.getName());
        response.setSurname(person.getLastName());
        response.setSkill(person.getLanguaje());
        response.setPassport(person.getPassport() != null);
        return response;
        }).toList();
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
    public PersonResponse patchPerson(Long id, PersonRequest personDto) {
        person person = personsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontro el usuario solicitado"));
    
        if (personDto.getName() != null) {
            person.setName(personDto.getName());
        }
        if (personDto.getSurname() != null) {
            person.setLastName(personDto.getSurname());
        }
        if (personDto.getSkill() != null) {
            person.setLanguaje(personDto.getSkill());
        }
        personsRepository.save(person);
       
        PersonResponse response = new PersonResponse();
        response.setName(person.getName());
        response.setSurname(person.getLastName());
        response.setSkill(person.getLanguaje());
        response.setPassport(person.getPassport() != null);
        return response;
    }

    @Override
    public PersonResponse createNewUser(PersonRequest personDto) {
       Optional<person> person = personsRepository.findPersonByPassportNumber(personDto.getPassport());

       if (person.isPresent()) {
        throw new RolDuplicateException("El usuario ya se encuentra registrado", HttpStatus.CONFLICT);
       }
       Rol userRol = roleRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("No se encuentra el rol"));

       // se creo un usuario temporal
       person newPerson = new person();
       newPerson.setName(personDto.getName());
       newPerson.setLastName(personDto.getSurname());
       newPerson.setLanguaje(personDto.getSkill());
       newPerson.setRole(userRol);

       //nuevo registro guardado
       person save = personsRepository.save(newPerson);

       //pasaporte temporal creado
       Passport passport = new Passport();
       passport.setPerson(save);
       passport.setNumber(personDto.getPassport());

    
       // guardar el pasaporte 
       documentReporitoy.save(passport);

       // mapeo de person a personResponse
       save.setPassport(passport);

       PersonResponse response = new PersonResponse();
        response.setName(save.getName());
        response.setSurname(save.getLastName());
        response.setSkill(save.getLanguaje());
        response.setPassport(save.getPassport() != null);
        return response;
    }
 
}
