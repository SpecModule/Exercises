package com.huyenho.demo.repository;

import com.huyenho.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IStudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByNameContaining(String name);
//    List<Student> findByNameContainingAndScoreBetween(String name);

    //khi có quá nhều attributes

    // cú pháp của HQL
    @Query("""
        FROM Student WHERE name like CONCAT('%', name, '%')
        AND (:fromScore IS NULL OR score >= :fromScore)
        AND (:toScore IS NULL OR score <= :toScore)
    """)
    List<Student> findByAttr(String name, Double fromScore, Double toScore);

}
