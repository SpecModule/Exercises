package com.huyenho.demo.service.impl;

import com.huyenho.demo.entity.Student;
import com.huyenho.demo.repository.IStudentRepository;
import com.huyenho.demo.service.IStudentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//cách 2
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//-----------------
public class StudentService implements IStudentService {
    //cách tiêm 1
//    @Autowired
//    private IStudentRepository studentRepository;

    //cách tiêm 2 - thực tế hay dùng
    IStudentRepository studentRepository;


    @Override
    public List<Student> findAll(String name, Double fromScore, Double toScore) {
        return studentRepository.findByAttr(name, fromScore, toScore);
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student update(int id, Student student) {
        Student stud = studentRepository.findById(id).get();
        stud.setName(student.getName());
        stud.setScore(student.getScore());
        studentRepository.save(stud);
        return stud;
    }

    @Override
    public List<Student> findByNameContaining(String name) {
        return studentRepository.findByNameContaining(name);
    }

    public List<Student> findByAttr(String name, double fromScore, double toScore) {
        return studentRepository.findByAttr(name, fromScore, toScore);
    }
}
