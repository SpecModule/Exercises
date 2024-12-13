package com.huyenho.demo.service.impl;

import com.huyenho.demo.model.Student;
import com.huyenho.demo.repository.IStudentRepository;
import com.huyenho.demo.repository.impl.StudentRepository;
import com.huyenho.demo.service.IStudentService;

import java.util.List;

public class StudentService implements IStudentService {
    private IStudentRepository studentRepository = new StudentRepository();

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

}
