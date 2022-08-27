package com.example.traineeApp.trainee;

import com.example.traineeApp.City.City;
import com.example.traineeApp.City.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//this class will contain all the resources for API
@RestController
//@RequestMapping(value="api/v1/trainee")
public class TraineeController {
    private final TraineeService traineeService;
    private final CityService cityService;

    @Autowired // this annotation used for injecting the object implicitly// its only for the reference  type
    public TraineeController(TraineeService traineeService, CityService cityService) {
        this.traineeService = traineeService;// instead of using new traineeService()
        this.cityService = cityService;
    }


    @GetMapping
    @RequestMapping(value = "api/trainees")
    public List<Trainee> getTrainee() {
        return traineeService.getTrainee();
    }

    @PostMapping
    @RequestMapping(value = "api/add")
    public void regTrainee(@RequestBody Trainee t) {
        traineeService.addTrainee(t);

    }

    @RequestMapping(value = "api/delete/{traineeName}")
    @DeleteMapping
    public void deleteTrainee(@PathVariable("traineeName") String traineeName) {
        traineeService.delete(traineeName);
    }

    @PutMapping
    @RequestMapping(value = "api/update/{traineeName}")
    public void update(@PathVariable("traineeName") String traineeName,
                       @RequestParam(required = false) String phoneNum,
                       @RequestParam(required = false) String name) {
        traineeService.update(traineeName, name, phoneNum);
    }

    @RequestMapping(value = "api/del/{traineeName}")
    @DeleteMapping
    public void dele(@PathVariable("traineeName") String traineeName) {
        traineeService.dele(traineeName);
    }

    @PutMapping("/api/{traineeId}/trainee/{cityId}")
    Trainee assign(@PathVariable Long traineeId, @PathVariable Long cityId){
        Trainee trainee = traineeService.findbyId(traineeId).get();
        City city = cityService.findbyId(cityId).get();
        trainee.assignCity(city);
        return traineeService.saveit(trainee);
    }





}

