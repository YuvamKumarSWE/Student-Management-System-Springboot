package com.yuvam.student.repository;

import com.yuvam.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository  extends JpaRepository<Student, Long> {
    List<Student> findByFirstName(String name);
    List<Student> findByLastName(String name);
    List<Student> findByEmail(String email);
    List<Student> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT s FROM Student s WHERE s.firstName = 'Yuvam'")
    List<Student> findMeCustom();

}
