package com.huyenho.demo.service;

import com.huyenho.demo.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();

    Student findById(int id);

    Student save(Student student);
}
