package com.acmsoft.checkgo.mapper;

import com.acmsoft.checkgo.dto.request.ArrivalLocationRequestDTO;
import com.acmsoft.checkgo.dto.response.ArrivalLocationResponseForUserDTO;
import com.acmsoft.checkgo.entity.ArrivalLocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

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
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ArrivalLocation toArrivalLocation(ArrivalLocationRequestDTO requestDTO);

    @Mapping(target = "publicId", source = "arrivalLocation.publicId")
    @Mapping(target = "locationName", source = "arrivalLocation.locationName")
    @Mapping(target = "address", source = "arrivalLocation.address")
    @Mapping(target = "tolerance", source = "arrivalLocation.toleranceMinutes")
    @Mapping(target = "radio", source = "arrivalLocation.radioAceptableMeters")
    @Mapping(target = "arrivalOnly", source = "arrivalLocation.arrivalOnly")
    @Mapping(target = "exitOnly", source = "arrivalLocation.exitOnly")
    @Mapping(target = "arrivalAndDeparture", source = "arrivalLocation.arrivalAndDeparture")
    @Mapping(target = "latitude", expression = "java(arrivalLocation.getLocation().getY())")
    @Mapping(target = "longitude", expression = "java(arrivalLocation.getLocation().getX())")
    @Mapping(target = "createdBy", source = "arrivalLocation.createdBy.fullname")
    @Mapping(target = "locationSchedules", ignore = true)
    ArrivalLocationResponseForUserDTO toDto(ArrivalLocation arrivalLocation);

    List<ArrivalLocationResponseForUserDTO> toDtoList(List<ArrivalLocation> arrivalLocations);
}
