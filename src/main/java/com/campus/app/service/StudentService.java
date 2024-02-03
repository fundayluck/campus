package com.campus.app.service;

import com.campus.app.entity.Student;
import com.campus.app.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public Student createStudent(Student student) {
      return repository.save(student);
    }

    public List<Student> getStudent() {
        return repository.findAll();
    }

    public Student getStudentById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Student updateStudent(Student student) {
        Student studentExisting = repository.findById(student.getId()).orElse(null);
        if(studentExisting != null) {
//            assert studentExisting != null;
            studentExisting.setName(student.getName());
            studentExisting.setDateOfBirth(student.getDateOfBirth());
            studentExisting.setAddress(student.getAddress());
            studentExisting.setNim(student.getNim());

            return repository.save(studentExisting);
        } else {
            return null;
        }
    }

    public Student deleteStudent(int id) {
        Optional<Student> studentToDeleteOptional = repository.findById(id);

        // Check if the student exists
        if (studentToDeleteOptional.isPresent()) {
            // Delete the student from the repository
            repository.deleteById(id);
            // Return the deleted student
            return studentToDeleteOptional.get();
        } else {
            // If the student is not found, you can handle it as needed (throw exception, return null, etc.)
            return null;
        }
    }

    public boolean isNimExists(String nim) {
        return repository.findByNim(nim) != null;
    }


}
