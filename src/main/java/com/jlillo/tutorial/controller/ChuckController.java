package com.jlillo.tutorial.controller;

import com.jlillo.tutorial.model.ChuckResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/chuck")
public interface ChuckController {

    String sayHello(String name, String color);

    @GetMapping("/hello")
    default String dSayHello(@RequestParam(value = "name") String name, @RequestParam(value = "color") String color){
        return sayHello(name,color);
    }

    @GetMapping("/joke")
    ChuckResponse randomJoke();
}
