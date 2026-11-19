package com.acmsoft.checkgo.mapper;

import com.acmsoft.checkgo.dto.request.ArrivalLocationDayRequestDto;
import com.acmsoft.checkgo.entity.ArrivalLocationDay;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ArrivalLocationDayMapper {
    @Mapping(target = "dayOfWeek", source = "arrivalLocationDayRequestDto.day")
    @Mapping(target = "enabled", source = "arrivalLocationDayRequestDto.enabled")
    @Mapping(target = "dayId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "arrivalLocation", ignore = true)
    ArrivalLocationDay toEntity(ArrivalLocationDayRequestDto arrivalLocationDayRequestDto);
}
