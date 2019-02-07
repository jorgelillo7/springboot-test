package com.jlillo.tutorial.controller;

import com.jlillo.tutorial.model.ChuckResponse;
import com.jlillo.tutorial.service.ChuckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChuckControllerImpl implements ChuckController {
    private final static Logger LOGGER = LoggerFactory.getLogger(ChuckControllerImpl.class);

    @Autowired
    ChuckService chuckService;

    @Override
    public String sayHello(String name, String color) {
        return String.format("Hello %s, your color is: %s", name, color);
    }

    @Override
    public ChuckResponse randomJoke() {
        return chuckService.randomJoke();
    }
}