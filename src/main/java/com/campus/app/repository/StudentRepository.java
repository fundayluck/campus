package com.campus.app.repository;

import com.campus.app.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findByNim(String nim);


}
