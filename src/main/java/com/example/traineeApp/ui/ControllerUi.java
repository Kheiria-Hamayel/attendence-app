package com.example.traineeApp.ui;

import com.example.traineeApp.City.City;
import com.example.traineeApp.City.CityService;
import com.example.traineeApp.trainee.Trainee;
import com.example.traineeApp.trainee.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Controller
public class ControllerUi {

    private final TraineeService traineeService;
    private final CityService cityService;
    ArrayList <String> search = new ArrayList<>();

    @Autowired
    public ControllerUi(TraineeService traineeService, CityService cityService) {
        this.traineeService = traineeService;
        this.cityService = cityService;
    }
    @GetMapping
    @RequestMapping(path = "/home")
    String start(Model model){
        model.addAttribute("cities",cityService.getCity());
        model.addAttribute("traineeList",traineeService.getTrainee());
        return "index";
    }

    @PostMapping(path = "/home")
    public String createEntry(@RequestParam String Name, @RequestParam String phone_number
            ,@RequestParam String city,@RequestParam("att") String att, Model model){
        boolean attend;
       // City c = new City(city);
       try {
           if (att.equalsIgnoreCase("true"))
               attend = true;
           else
               attend = false;

           City c = cityService.findbyName(city).get();
           Trainee t = new Trainee(Name, phone_number,city,attend);
           t.assignCity(c);
           traineeService.addTrainee(t);
           return "redirect:/home";
       }catch (IllegalStateException e){
           model.addAttribute("mess",e.getMessage());
           return "error/500";
       }

    }



    @GetMapping
    @RequestMapping(path = "/delete")
    public String renderDeleteTrainee(){
        return "delete";
    }

    @PostMapping(path= "/delete")
    public String deleteTrainee(@RequestParam String Namee,Model model) {
        try {
            traineeService.delete(Namee);
            return "redirect:/home";
        } catch (IllegalStateException e) {
            model.addAttribute("mess", e.getMessage());
            return "error/500";
        }

    }
    @GetMapping("/search")
    public String renderForm(Model model){

        search.add("id");
        search.add("name");
        search.add("phone number");
        search.add("attendance");
        model.addAttribute("option",search);
        return "search";
    }
    @PostMapping(path= "/search")
    public String searchForm(@RequestParam String options,@RequestParam String picked,Model model){
        model.addAttribute("option",search);

       if(options.equalsIgnoreCase("id")){
           long id = Long.parseLong(picked);
           model.addAttribute("result",traineeService.searchById(id));
        }
       if(options.equalsIgnoreCase("name")){
            model.addAttribute("result",traineeService.searchByName(picked));
        }
        if(options.equalsIgnoreCase("phone number")){

            model.addAttribute("result",traineeService.searchByPhone(picked));
        }
        if (options.equalsIgnoreCase("attendance")){
            boolean att = Boolean.parseBoolean(picked);
            model.addAttribute("result",traineeService.searchByAttendance(att));
        }

        return "search";

    }


}