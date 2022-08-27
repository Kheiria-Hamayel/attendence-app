package com.example.traineeApp;

import com.example.traineeApp.City.City;
import com.example.traineeApp.City.CityRepository;
import com.example.traineeApp.trainee.Trainee;
import com.example.traineeApp.trainee.TraineeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class TraineeConfig {
    @Bean
    CommandLineRunner commandLineRunner (TraineeRepository repository, CityRepository cityRepository ){
  
        return args -> {
           Trainee Amy =  new Trainee(
                    "Amy",
                    "+970598123456",
               "Ramallah",
                   true
            );
            Trainee Bob =  new Trainee(
                    "Bob",
                    "+970598654321",
                  "Nablus",
                    true
            );
            repository.saveAll(List.of(Amy,Bob));

            City c1 =  new City( "Ramallah");
            City c2 =  new City( "Nablus");
            City c3 =  new City( "Hebron");
            City c4 =  new City( "Jenin");
            cityRepository.saveAll(List.of(c1,c2,c3,c4));


        };
    }
}
