package com.campus.app.controller;


import com.campus.app.entity.Student;
import com.campus.app.response.StudentResponse;
import com.campus.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping("/create")
    public ResponseEntity<StudentResponse<Student>> createStudent(@RequestBody Student student) {
        if(service.isNimExists(student.getNim())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StudentResponse<>(false, 400, "NIM already exists", null));
        }
        Student createStudent = service.createStudent(student);
        return ResponseEntity.status(HttpStatus.OK).body(new StudentResponse<>(true, 200, "success created Student", null));
    }

    @GetMapping("/all")
    public ResponseEntity<StudentResponse<List<Student>>> getStudent() {
       List<Student> students = service.getStudent();
       if(!students.isEmpty()) {
           return ResponseEntity.status(HttpStatus.OK).body(new StudentResponse<>(true, 200, "Success get all data", students));
       } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StudentResponse<>(false, 404, "data not found", null));
       }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse<Student>> getStudentById(@PathVariable int id) {
        Student student = service.getStudentById(id);
        if(student != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new StudentResponse<>(true, 200, "Success get data", student));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StudentResponse<>(false, 404, "Student not found", null));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<StudentResponse<Student>> updateStudent(@RequestBody Student student) {
        Student updateStudent = service.updateStudent(student);
        if(updateStudent != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new StudentResponse<>(true, 200, "Success update data", student));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StudentResponse<>(false, 404, "not data found!!", null));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StudentResponse<Student>> deleteStudent(@PathVariable int id) {
        Student deletedStudent = service.deleteStudent(id);

        if (deletedStudent != null) {
            // Return success response if the student was found and deleted
            return ResponseEntity.ok(new StudentResponse<>(true, 200, "Success delete data", deletedStudent));
        } else {
            // Return error response if the student was not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StudentResponse<>(false, 404, "Student not found", null));
        }
    }

}
