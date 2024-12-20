package com.huyenho.demo.service.impl;

import com.huyenho.demo.dto.PremisesSearchRequest;
import com.huyenho.demo.entity.Premises;
import com.huyenho.demo.repository.IPremisesRepository;
import com.huyenho.demo.service.IPremisesService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PremisesService implements IPremisesService {
    IPremisesRepository premisesRepository;

    @Override
    public Page<Premises> findByAttributes(PremisesSearchRequest premisesSearchRequest, Pageable pageable) {
        return premisesRepository.findByAttributes(premisesSearchRequest, pageable);
    }

    @Override
    public Premises updatePremises(String id, Premises updatedData) {
        Premises premises = premisesRepository.findById(id);
        premises.setName(updatedData.getName());
        premises.setAddress(updatedData.getAddress());
        premises.setArea(updatedData.getArea());
        premises.setType(updatedData.getType());
        premises.setRentPrice(updatedData.getRentPrice());
        premises.setStartDate(updatedData.getStartDate());
        premises.setDescription(updatedData.getDescription());
        premisesRepository.save(premises);
        return premises;
    }

    @Override
    public Premises deletePremises(String id) {
        return premisesRepository.deleteById(id);
    }

    @Override
    public Optional<Premises> getPremises(String id) {
        return Optional.ofNullable(premisesRepository.findById(id));
    }

    @Override
    public Premises addPremises(Premises premises) {
        return premisesRepository.save(premises);
    }
}
