package com.yuvam.student.services;

import com.yuvam.student.models.Student;
import com.yuvam.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findMe() {
        return studentRepository.findMeCustom();
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student, Long id) {
        return studentRepository.findById(id).map(
                existingStudent -> {
                    existingStudent.setFirstName(student.getFirstName());
                    existingStudent.setLastName(student.getLastName());
                    existingStudent.setEmail(student.getEmail());
                    existingStudent.setPassword(student.getPassword());

                    return studentRepository.save(existingStudent);
                }
        ).orElseThrow(
                () -> new RuntimeException("Student not found with id " + id)
        );
    }

    public void deleteStudent(Long id) throws Exception {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new Exception("Student with id " + id + " not found"));
        studentRepository.deleteById(id);
    }
}
