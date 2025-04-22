package com.example.hellowordl.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.domain.producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ApiController {
    
    @GetMapping("/")
    public String home(){
        return "home de camper";
    }

    @GetMapping("/saludo")// /saludo?nombre=franco
    public String saludo(@RequestParam(name= "nombre", required = true)String name ,
    @RequestParam(name = "apellido", required = false, defaultValue = "Apellido comun") String lastName
    ){
        return "hello" + " " + name + lastName;
    }



    @GetMapping("/search")
    public Map<String, String> buscar(
        @RequestParam(name= "nombre", defaultValue = "Apellido comun")String name 
    ){

        Map<String, String> cities = new HashMap<>();

        cities.put("BUC", "Bucaramanga");
        cities.put("NYC", "New York City");
        cities.put("BOG", "Bogota");
        cities.put("NVA", "Neiva");
        cities.put("LET", "Letiia");


        if (cities.containsKey(name)) {
            return Map.of(name, cities.get(name));
        }
        else{
            return cities;
        }

    }



    @GetMapping("/tax")
    public Map<String, Object> calcular(
        @RequestParam( defaultValue = "0")double impuestos 
    ){
        List<producto> productos = new ArrayList<>();

        productos.add(new producto(1, "gaseosa", 2000));
        productos.add(new producto(2, "pan", 1000));
        productos.add(new producto(3, "salchichon", 3000));

        double total = 0;
        double valor_neto = 0;

        for(producto p:productos){
            total += p.getPrice();
        }

        impuestos = impuestos/100;

        valor_neto = impuestos*total+total;

        return Map.of("productos", productos, "total", valor_neto, "valor_neto", total);
    }

}
