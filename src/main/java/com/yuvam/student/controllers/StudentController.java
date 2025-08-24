package com.yuvam.student.controllers;

import com.yuvam.student.models.Student;
import com.yuvam.student.responses.ApiResponse;
import com.yuvam.student.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    StudentService studentService;


    @Autowired
    public StudentController( @Qualifier("studentService") StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/getAll")
    public ApiResponse<List<Student>> findAll() {
        List<Student> students = studentService.findAll();
        return new ApiResponse<>(
                true,
                "Successfully fetched all students",
                students,
                students.size()
        );
    }

    @GetMapping("/me")
    public ApiResponse<Student> getMe() {
        Student me = studentService.findMe();
        return new ApiResponse<>(
                true,
                "Successfully fetched you!",
                me,
                1
        );
    }

    @PostMapping("/addStudent")
    public ApiResponse<Student> addStudent(@RequestBody Student student) {
        Student newStudent = studentService.addStudent(student);
        return new ApiResponse<>(
                true,
                "Successfully added student",
                newStudent,
                1
        );
    }

    @PutMapping("/updateStudent/{id}")
    public ApiResponse<Student> updateStudent(@RequestBody Student student, @PathVariable Long id) {
        Student updatedStudent = studentService.updateStudent(student,id);
        return new ApiResponse<>(
                true,
                "Successfully updated student",
                updatedStudent,
                1
        );
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ApiResponse<String> deleteStudent(@PathVariable Long id) throws Exception {
        studentService.deleteStudent(id);
        return new ApiResponse<>(
                true,
                "Student deleted successfully",
                "Deleted ID: " + id,
                null
        );
    }





}
