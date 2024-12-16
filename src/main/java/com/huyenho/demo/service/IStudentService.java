package com.huyenho.demo.service;

import com.huyenho.demo.emtity.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();

    Student findById(int id);

    Student save(Student student);

    Student update(int id, Student student);

    List<Student> findByNameContaining(String name);

    List<Student> findByAttr(String name, double fromScore, double toScore);
}
