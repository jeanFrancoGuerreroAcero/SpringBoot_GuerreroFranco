package com.domain;

public class producto {
    
    private int id;
    private String name;
    private double price;

    public producto(int id, String name, double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId(){
        return id;
    }

    public String getname(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
