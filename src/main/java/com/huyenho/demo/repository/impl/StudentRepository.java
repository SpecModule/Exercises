package com.huyenho.demo.repository.impl;

import com.huyenho.demo.model.Student;
import com.huyenho.demo.repository.IStudentRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentRepository implements IStudentRepository {
    private List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student(1, "Am hii", 9.7),
                    new Student(2, "Duc Hung", 9.6)
            )
    );

    public List<Student> findAll() {
        return students;
    }

    public Student findById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    public Student save(Student student) {
        student.setId((int) (Math.random() * 100000));
        students.add(student);
        return student;
    }

}
