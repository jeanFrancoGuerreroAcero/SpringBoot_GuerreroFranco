package com.example.dia4.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name  = "personas")
//Bidireccional
//manytoOne
//onetoMany
//oneToOne
//manyToMany (Llaves compuestas)

public class person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    
    @Column(name = "programming_languaje")
    private String languaje;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    @JsonBackReference //marcar el lado que no se serializa
    private Rol role;

    public person() {
    }

    public person(Long id, String name, String lastName, String languaje) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.languaje = languaje;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLanguaje() {
        return languaje;
    }

    public void setLanguaje(String languaje) {
        this.languaje = languaje;
    }

    public person(Rol role) {
        this.role = role;
    }

    public Rol getRole() {
        return role;
    }

    public void setRole(Rol role) {
        this.role = role;
    }
}
