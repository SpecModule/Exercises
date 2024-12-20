package com.huyenho.demo.service;

import com.huyenho.demo.dto.PremisesSearchRequest;
import com.huyenho.demo.entity.Premises;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IPremisesService {
    Page<Premises> findByAttributes(PremisesSearchRequest premisesSearchRequest, Pageable pageable);
    Premises updatePremises(String id, Premises updatedData);
    Premises deletePremises(String id);
    Optional<Premises> getPremises(String id);
    Premises addPremises(Premises premises);
}
