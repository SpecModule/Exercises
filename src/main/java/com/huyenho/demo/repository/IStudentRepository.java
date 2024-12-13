package com.huyenho.demo.repository;

import com.huyenho.demo.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public interface IStudentRepository {
    List<Student> findAll();

    Student findById(int id);

    Student save(Student student);
}
