package com.example.dia4.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.dia4.applicatin.service.RoleService;
import com.example.dia4.domain.Rol;
import com.example.dia4.infraestructure.error.RolDuplicateException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository rolRepository;

    public RoleServiceImpl(RoleRepository rolRepository) {
        this.rolRepository = rolRepository;
    }


    @Override 
    public List<Rol> finByRolesByFilter(String filter, String value){
        return rolRepository.findAll();
    }

    @Override
    public Rol createNewRol(String name){
        Rol newRol = new Rol();
        newRol.setName(name);
        if(getRolByName(name).isPresent()){
            throw new RolDuplicateException("El rol " + name +  " ya esta registrado", 
            HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return rolRepository.save(newRol);
    }

    public Optional<Rol> getRolByName(String name){
        return rolRepository.findByName(name);
    }

    @Override
    public Rol removeRol(Long id){
        Optional<Rol> rol= rolRepository.findById(id);
        if(!rol.isPresent()){
            throw new EntityNotFoundException("El rol no se encuentra registrado");
        }
        if(!rol.get().getPersons().isEmpty()){
            throw new EntityNotFoundException("El rol se encuentra asociado con usuarios");
        }
        rolRepository.deleteById(id);
        return rol.get();
    }
}