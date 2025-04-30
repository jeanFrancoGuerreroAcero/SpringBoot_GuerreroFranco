package com.example.dia4.domain;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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

    @Column(name = "full_name", columnDefinition = "TEXT", length = 50, nullable = false)
    private String name;
    private String lastName;
    
    @Column(name = "programming_languaje")
    private String languaje;

    @ManyToOne(cascade = CascadeType.ALL) //nivel de JPA
    @JoinColumn(name = "rol_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) //nivel de base de datos
    @JsonBackReference //marcar el lado que no se serializa
    private Rol role;

    @OneToOne(mappedBy = "person")
    @JsonManagedReference
    private Passport passport;

    @ManyToMany
    @JsonBackReference
    @JoinTable(name = "personas_project", joinColumns = @JoinColumn(name = "persona_id" , foreignKey = @ForeignKey(name = "fk_persona_id_projects")),inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Project> projects = new ArrayList<>();

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
