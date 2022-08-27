package com.example.traineeApp.City;

import com.example.traineeApp.trainee.Trainee;
import com.example.traineeApp.trainee.TraineeRepository;
import com.example.traineeApp.trainee.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController {
    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;// instead of using new traineeService()
    }

    @GetMapping
    @RequestMapping(value = "api/city")
    public List<City> bring() {
        return cityService.getCity();
    }

    @PostMapping
    @RequestMapping(value = "api/city/add")
    public void regTrainee(@RequestBody City t) {
        cityService.addCity(t);

    }

}
