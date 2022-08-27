package com.example.traineeApp;

import com.example.traineeApp.trainee.Trainee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class TraineeAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraineeAppApplication.class, args);
	}


}
