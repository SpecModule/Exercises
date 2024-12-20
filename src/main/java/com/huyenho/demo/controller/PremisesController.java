package com.huyenho.demo.controller;

import com.huyenho.demo.dto.JsonResponse;
import com.huyenho.demo.dto.PremisesSearchRequest;
import com.huyenho.demo.dto.exception.AppException;
import com.huyenho.demo.dto.exception.ErrorCode;
import com.huyenho.demo.dto.premises.PremisesRequest;
import com.huyenho.demo.dto.premises.PremisesResponse;
import com.huyenho.demo.entity.Premises;
import com.huyenho.demo.mapper.IPremisesMapper;
import com.huyenho.demo.service.IPremisesService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/premises")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PremisesController {
    IPremisesService premisesService;
    IPremisesMapper premisesMapper;

    @GetMapping("")
    public ResponseEntity<?> getAllPremises(PremisesSearchRequest premisesSearchRequest,
                                             @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Premises> premises = premisesService.findByAttributes(premisesSearchRequest, pageable);

        Page<PremisesResponse> premisesResponses = premises.map(premisesMapper::premisesToPremisesResponse);

        return ResponseEntity.ok(premisesResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPremises(@PathVariable String id) {
        Optional<Premises> pre = premisesService.getPremises(id);
        if (pre.isEmpty()) {
            throw new AppException(ErrorCode.PREMISES_NOT_EXIST);
        }

        PremisesResponse premisesResponse = premisesMapper.premisesToPremisesResponse(pre.get());

        return JsonResponse.ok(premisesResponse);
    }

    @PostMapping("")
    public ResponseEntity<?> addPremises(@Valid @RequestBody PremisesRequest premisesRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors().forEach(error -> {
                String nameError = ((FieldError) error).getField();
                String messageError = error.getDefaultMessage();
                errors.put(nameError, messageError);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        Premises premises = premisesMapper.premisesRequestToPremises(premisesRequest);

        premisesService.addPremises(premises);

        PremisesResponse premisesResponse = premisesMapper.premisesToPremisesResponse(premises);

        return JsonResponse.created(premisesResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePremises(@PathVariable String id,@Valid @RequestBody PremisesRequest updatedData, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors().forEach(error -> {
                String nameError = ((FieldError) error).getField();
                String messageError = error.getDefaultMessage();
                errors.put(nameError, messageError);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        Premises premises = premisesMapper.premisesRequestToPremises(updatedData);

        Premises updatedPremises = premisesService.updatePremises(id, premises);

        if (updatedPremises == null) {
            throw new AppException(ErrorCode.PREMISES_NOT_EXIST);
        }

        PremisesResponse premisesResponse = premisesMapper.premisesToPremisesResponse(updatedPremises);

        return JsonResponse.ok(premisesResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePremises(@PathVariable String id) {
        premisesService.deletePremises(id);
        return JsonResponse.noContent();
    }
}
