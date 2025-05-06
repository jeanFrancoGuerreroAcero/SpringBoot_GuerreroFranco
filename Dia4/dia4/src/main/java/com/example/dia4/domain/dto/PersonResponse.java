package com.example.dia4.domain.dto;

public class PersonResponse {
    private String name, surname, Skill;
    private boolean passport;

    public PersonResponse() {
    }

    
    public PersonResponse(String name, String surname, String skill, boolean passport) {
        this.name = name;
        this.surname = surname;
        Skill = skill;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSkill() {
        return Skill;
    }

    public void setSkill(String skill) {
        Skill = skill;
    }

    public boolean isPassport() {
        return passport;
    }

    public void setPassport(boolean passport) {
        this.passport = passport;
    }

    


}
