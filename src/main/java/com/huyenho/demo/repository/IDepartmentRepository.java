package com.huyenho.demo.repository;

import com.huyenho.demo.emtity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDepartmentRepository extends JpaRepository<Department, Integer> {
}
