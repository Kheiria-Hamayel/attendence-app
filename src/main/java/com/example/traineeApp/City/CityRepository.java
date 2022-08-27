package com.example.traineeApp.City;

import com.example.traineeApp.trainee.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query("SELECT t From City t WHERE t.cityName = ?1")
    Optional<City> findByName(String cityName);
}
