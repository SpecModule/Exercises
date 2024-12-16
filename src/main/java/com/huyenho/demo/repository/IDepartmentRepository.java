package com.huyenho.demo.repository;

import com.huyenho.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepartmentRepository extends JpaRepository<Department, Integer> {
}
