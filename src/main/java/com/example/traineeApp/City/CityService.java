package com.example.traineeApp.City;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getCity() {
        //City c1 =  new City( "Ramallah");
       // City c2 =  new City( "Nablus");
       return cityRepository.findAll();
    }

    public void addCity(City c) {
        cityRepository.save(c);
    }

    public Optional<City> findbyId(Long traineeId) {
       return cityRepository.findById(traineeId);
    }

    public Optional<City> findbyName(String cityName ) {
        return cityRepository.findByName(cityName);
    }

}
