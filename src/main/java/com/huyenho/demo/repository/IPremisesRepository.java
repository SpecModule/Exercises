package com.huyenho.demo.repository;

import com.huyenho.demo.dto.PremisesSearchRequest;
import com.huyenho.demo.entity.Premises;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPremisesRepository extends JpaRepository<Premises, Integer> {
    @Query("""
        FROM Premises
        WHERE (:#{#premisesSearchRequest.id} IS NULL OR id = :#{#premisesSearchRequest.id})\s
          AND (:#{#premisesSearchRequest.name} IS NULL OR name LIKE CONCAT('%', :#{#premisesSearchRequest.name}, '%'))\s
          AND (:#{#premisesSearchRequest.address} IS NULL OR address LIKE CONCAT('%', :#{#premisesSearchRequest.address}, '%'))\s
          AND (:#{#premisesSearchRequest.areaFrom} IS NULL OR area >= :#{#premisesSearchRequest.areaFrom})\s
          AND (:#{#premisesSearchRequest.areaTo} IS NULL OR area <= :#{#premisesSearchRequest.areaTo})\s
          AND (:#{#premisesSearchRequest.type} IS NULL OR type = :#{#premisesSearchRequest.type})\s
          AND (:#{#premisesSearchRequest.rentPriceRange} IS NULL OR\s
                (:#{#premisesSearchRequest.rentPriceRange} = 'UNDER_2M' AND rentPrice < 2000000) OR\s
                (:#{#premisesSearchRequest.rentPriceRange} = '2M_TO_5M' AND rentPrice >= 2000000 AND rentPrice < 5000000) OR\s
                (:#{#premisesSearchRequest.rentPriceRange} = '5M_TO_10M' AND rentPrice >= 5000000 AND rentPrice < 10000000) OR\s
                (:#{#premisesSearchRequest.rentPriceRange} = 'OVER_10M' AND rentPrice >= 10000000))\s
          AND (:#{#premisesSearchRequest.fromDate} IS NULL OR startDate >= :#{#premisesSearchRequest.fromDate})\s
          AND (:#{#premisesSearchRequest.toDate} IS NULL OR startDate <= :#{#premisesSearchRequest.toDate})
    """)
    Page<Premises> findByAttributes(PremisesSearchRequest premisesSearchRequest, Pageable pageable);

    Premises findById(String id);

    Premises deleteById(String id);
}
