package com.example.traineeApp.ui;

import com.example.traineeApp.trainee.Trainee;
import com.example.traineeApp.trainee.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@Controller
public class EventController {

    private final TraineeService traineeService;

    @Autowired // this annotation used for injecting the object implicitly// its only for the reference  type
    public EventController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }


    @GetMapping(path = "/events/create")
    public  String CreateForm(){
        return "events/create";
    }
/*
    @PostMapping(path = "/events/create")
    public String createEntry(@RequestParam String Name, @RequestParam String phone_number ){
        traineeService.addTrainee(new Trainee(Name,phone_number,));
        return "redirect:/home";

    }
*/
}
