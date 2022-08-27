package com.example.traineeApp.City;

import com.example.traineeApp.trainee.Trainee;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "City")
@Table
public class City {
    @Id
    @SequenceGenerator(
            name = "city_sequence",
            sequenceName = "city_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "city_sequence"
    )
    @Column(
            name = "id",
            columnDefinition = "int default 0",
            updatable = false
    )
    private long id;
    @Column(
            name = "city_name",
            nullable = false,
            columnDefinition = "Text"
    )
    private String cityName;


    @JsonIgnore
    @OneToMany(mappedBy="city")
    private Set<Trainee> traineeSet = new HashSet<>();

    public City() {
    }

    public City( String cityName) {
        this.cityName = cityName;
    }

    public Set<Trainee> getTraineeSet() {
        return traineeSet;
    }

    public void setTraineeSet(Set<Trainee> traineeSet) {
        this.traineeSet = traineeSet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
