package com.huyenho.demo.service.impl;

import com.huyenho.demo.model.Student;
import com.huyenho.demo.repository.IStudentRepository;
import com.huyenho.demo.repository.impl.StudentRepository;
import com.huyenho.demo.service.IStudentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
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
