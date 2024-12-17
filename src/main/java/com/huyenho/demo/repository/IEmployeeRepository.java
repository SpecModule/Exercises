package com.huyenho.demo.repository;

import com.huyenho.demo.dto.EmployeeSearchRequest;
import com.huyenho.demo.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("""
        FROM Employee 
        WHERE (:#{#employeeSearchRequest.name} IS NULL OR name LIKE CONCAT('%', :#{#employeeSearchRequest.name}, '%'))
        AND (:#{#employeeSearchRequest.dobFrom} IS NULL OR birthday >= :#{#employeeSearchRequest.dobFrom})
        AND (:#{#employeeSearchRequest.dobTo} IS NULL OR birthday <= :#{#employeeSearchRequest.dobTo})
        AND (:#{#employeeSearchRequest.gender} IS NULL OR gender = :#{#employeeSearchRequest.gender})
        AND (:#{#employeeSearchRequest.salaryRange} = 0 OR salary >= :#{#employeeSearchRequest.salaryRange})
        AND (:#{#employeeSearchRequest.phone} IS NULL OR phone LIKE CONCAT('%', :#{#employeeSearchRequest.phone}, '%'))
        AND (:#{#employeeSearchRequest.department} IS NULL OR department.id = :#{#employeeSearchRequest.department})
    """)
    Page<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest, Pageable pageable);
}
