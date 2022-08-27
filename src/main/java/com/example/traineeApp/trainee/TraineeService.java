package com.example.traineeApp.trainee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@Component // to mark this class as a component for auto detection this class for DI
@Service
public class TraineeService {
    private final TraineeRepository traineeRepository;

    @Autowired
    public TraineeService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    public static String upperCaseFirst(String val) {
        char[] arr = val.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
    }

    public List<Trainee> getTrainee() {

        return traineeRepository.findAll();
    }


    public void addTrainee(Trainee trainee) {
        if (!trainee.getPhoneNum().startsWith("+")) {
            trainee.setPhoneNum("+" + trainee.getPhoneNum());
        }
        /*if (trainee.getCity().equalsIgnoreCase("---"))
            throw new IllegalStateException("You entered invalid city");*/

        if (namePatteren(trainee))
            throw new IllegalStateException("You have invalid character inside the name field");


        if (numPatteren(trainee))
            throw new IllegalStateException("You have invalid character inside the phone number field");

        Optional<Trainee> opt = traineeRepository.findByPhoneNum(trainee.getPhoneNum());
        if (trainee.getName().equalsIgnoreCase(" ") ||
                trainee.getPhoneNum().equalsIgnoreCase(" ") ||
                trainee.getPhoneNum().length() != 13) {
            throw new IllegalStateException("You have to enter a valid name or a  valid phone number");
        }
        if (opt.isPresent()) {
            throw new IllegalStateException(" This phone number was used");

        } else {
            trainee.setName(upperCaseFirst(trainee.getName()));
            traineeRepository.save(trainee);
           //traineeRepository.insertTrainee(trainee.getName(),trainee.getPhoneNum());
            System.out.println(trainee);
        }
    }

    @Transactional
    public void delete(String traineeName) {
        List<Trainee> opt = traineeRepository.findByNameD(traineeName.toUpperCase());
        if (opt.size() > 1) {
            throw new IllegalStateException("there is more than one  trainee with this name = " + traineeName);
        }
        if (opt.size() == 1)
            traineeRepository.deleteByName(traineeName);

        else
            throw new IllegalStateException("the trainee with this name = " + traineeName + " is not exists");
    }


    @Transactional
    public void update(String traineeName, String name, String phoneNum) {
        phoneNum = '+' + phoneNum;
        List<Trainee> opt = traineeRepository.findByNameD(traineeName);
        if (opt.size() > 1) {
            throw new IllegalStateException("there is more than one  trainee with this name = " + traineeName);
        }

        Optional<Trainee> trainee2 = traineeRepository.findByPhoneNum(phoneNum);
        if (!trainee2.isPresent()) {

            Trainee trainee = traineeRepository.findByName(traineeName).
                    orElseThrow(() -> new IllegalStateException("the trainee with this name = " + traineeName + " is not exists"));


            if (phoneNum != null && phoneNum.length() == 13) {
                trainee.setPhoneNum(phoneNum);
            } else {
                throw new IllegalStateException("Invalid Phone number");
            }

            if (name != null && name.length() > 0) {
                trainee.setName(name);
            }// else{
            // throw new IllegalStateException("name must be not null");
            //}
        } else {
            throw new IllegalStateException("this number was used");
        }
    }

    public void dele(String traineeName) {
        traineeRepository.del(traineeName);
    }

    public boolean namePatteren(Trainee trainee){
        Pattern my_pattern = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
        Matcher my_match = my_pattern.matcher(trainee.getName());
        boolean check = my_match.find();
        return check;
    }

    public boolean numPatteren(Trainee trainee){
        Pattern mpattern = Pattern.compile("[^0-9+]", Pattern.CASE_INSENSITIVE);
        Matcher mmatch = mpattern.matcher(trainee.getPhoneNum());
        boolean mcheck = mmatch.find();
        return mcheck;
    }


    public Optional<Trainee> findbyId(Long traineeId) {
       return traineeRepository.findById(traineeId);
    }

    public Trainee saveit(Trainee trainee) {
        return  traineeRepository.save(trainee);
    }

    public List<Trainee> searchById(Long id) {
        return traineeRepository.searchById(id);
    }

    public List<Trainee> searchByName(String picked) {
        return traineeRepository.searchByName(picked);
    }

    public List<Trainee> searchByPhone(String picked) {

        return traineeRepository.searchByPhone(picked);
    }

    public List<Trainee> searchByAttendance(Boolean picked) {
        return traineeRepository.searchByattend(picked);
    }
}
