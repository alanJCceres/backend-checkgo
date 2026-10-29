package com.acmsoft.checkgo.mapper;

import com.acmsoft.checkgo.dto.request.ArrivalLocationRequestDTO;
import com.acmsoft.checkgo.entity.ArrivalLocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ArrivalLocationMapper {
    @Mapping(target = "locationName", source = "requestDTO.locationName")
    @Mapping(target = "address", source = "requestDTO.address")
    @Mapping(target = "toleranceMinutes", source = "requestDTO.tolerance")
    @Mapping(target = "radioAceptableMeters", source = "requestDTO.radio")
    @Mapping(target = "arrivalOnly", source = "requestDTO.arrivalOnly")
    @Mapping(target = "exitOnly", source = "requestDTO.exitOnly")
    @Mapping(target = "arrivalAndDeparture", source = "requestDTO.arrivalAndDeparture")
    @Mapping(target = "locationId", ignore = true)
    @Mapping(target = "publicId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "location", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "arrivalLocationSchedules", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ArrivalLocation toArrivalLocation(ArrivalLocationRequestDTO requestDTO);

    //ArrivalLocationRequestDTO toDto(ArrivalLocation arrivalLocation);
}
