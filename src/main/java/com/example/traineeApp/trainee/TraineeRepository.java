package com.example.traineeApp.trainee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Long> { // the T in the repositories is general (GENERICS)

    @Query("SELECT t From Trainee t WHERE t.phoneNum = ?1")
    Optional<Trainee> findByPhoneNum(String phoneNum);

    @Query("SELECT t From Trainee t WHERE t.name = ?1")
    Optional<Trainee> findByName(String name);

    @Query("SELECT t From Trainee t WHERE t.name = ?1")
    List<Trainee> searchByName(String name);

    @Query("SELECT t From Trainee t WHERE t.id = ?1")
    List<Trainee> searchById(long id);

    @Query("SELECT t From Trainee t WHERE t.phoneNum = ?1")
    List<Trainee> searchByPhone(String phoneNum);
    @Query("SELECT t From Trainee t WHERE t.attendance = ?1")
    List<Trainee> searchByattend(Boolean att);


    @Transactional
    @Modifying
    @Query(value = "insert into Trainee (id,name,phone_num) VALUES (0,:name,:phoneNum)",nativeQuery = true)
    void insertTrainee( @Param("name") String name ,@Param("phoneNum") String phoneNum);


    ///
    @Query("SELECT t From Trainee t WHERE UPPER(t.name) = ?1")
    List<Trainee> findByNameD(String name);

   // @Query("DELETE FROM Trainee t WHERE t.name=name ")
    void deleteByName( String name);
    @Transactional
    @Modifying
    @Query("DELETE from Trainee t WHERE t.name=?1")
    void del(String name);



    /*@Modifying(clearAutomatically = true)
    @Query(value = "update Trainee t set t.phone_num = phone_num where t.name = name " , nativeQuery = true)
    @Transactional
    void update (@Param("phone_num") String phone_num , @Param("name") String name );*/

   /* @Query("select t from Trainee where t.name = :name")
    List<Trainee> ss (@Param("name") String name);
    //void update (@Param("phone_num") String phone_num , @Param("name") String name );*/
}
