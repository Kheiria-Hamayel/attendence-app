package com.example.traineeApp.trainee;

import com.example.traineeApp.City.City;

import javax.persistence.*;
import java.util.ArrayList;

@Entity(name = "Trainee")
@Table
public class Trainee {
    @Id
    @SequenceGenerator(
            name = "trainee_sequence",
            sequenceName = "trainee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "trainee_sequence"
    )
    @Column(
            name = "id",
            columnDefinition = "int default 0",
            updatable = false
    )
    private long id;
    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "Text"
    )
    private String name;
    @Column(
            name = "phone_num",
            nullable = false,
            columnDefinition = "Text"
    )
    private String phoneNum;
 /*   @Column(
            name = "city",
            nullable = false,
            columnDefinition = "Text"
    )
    private String city;*/
    @Column(
            name = "attendance",
            nullable = false,
            columnDefinition = "boolean default True"
    )
    private boolean attendance;


    String cit;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id",referencedColumnName = "id")
   private City city;

    protected Trainee() {
    }


    // constructor without id since the database will generate it
    public Trainee(String name, String phoneNum , String cit ,  boolean attendance) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.cit = cit;
        this.attendance = attendance;
    }

    @Override
    public String toString() {
        return "Trainee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", city='" + city + '\'' +
                ", attendance=" + attendance +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCity() {
        return cit;
    }

    public void setCity(String city) {
        this.cit = cit;
    }

    public boolean getAttendance() {
        return attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }

    public City getCityy() {
        return city;
    }

    public void setCityy(City city) {
        this.city = city;
    }

    public City assignCity(City city) {
        return this.city = city;
    }
}
