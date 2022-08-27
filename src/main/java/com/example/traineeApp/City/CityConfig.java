//package com.example.traineeApp.City;

import com.example.traineeApp.City.City;
import com.example.traineeApp.City.CityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CityConfig {
    @Bean
    CommandLineRunner commandLineRunner (CityRepository repository){
        return args -> {
            City c1 =  new City( "Ramallah");
            City c2 =  new City( "Nablus");
            repository.saveAll(List.of(c1,c2));

        };
    }
}
