package com.huyenho.demo.mapper;

import com.huyenho.demo.dto.premises.PremisesRequest;
import com.huyenho.demo.dto.premises.PremisesResponse;
import com.huyenho.demo.entity.Premises;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPremisesMapper {
    PremisesResponse premisesToPremisesResponse(Premises premises);
    Premises premisesRequestToPremises(PremisesRequest premisesRequest);
}
