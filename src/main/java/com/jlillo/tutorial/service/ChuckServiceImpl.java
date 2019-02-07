package com.jlillo.tutorial.service;


import com.jlillo.tutorial.model.ChuckResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Service
public class ChuckServiceImpl implements ChuckService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ChuckServiceImpl.class);
    private static final String API_CHUCKNORRIS_IO_JOKES_RANDOM = "https://api.chucknorris.io/jokes/random";

    @Override
    @GetMapping("/joke")
    public ChuckResponse randomJoke() {
        LOGGER.info("Start randomJoke");
        RestTemplate restTemplate = new RestTemplate();
        ChuckResponse response = restTemplate.getForObject(
                API_CHUCKNORRIS_IO_JOKES_RANDOM,
                ChuckResponse.class);
        LOGGER.info(String.format("RandomJoke: %s", response.getValue()));
        return response;
    }
}