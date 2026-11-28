package com.acmsoft.checkgo.mapper;

import com.acmsoft.checkgo.dto.request.ArrivalLocationDayRequestDTO;
import com.acmsoft.checkgo.dto.response.ArrivalLocationDayResponseDTO;
import com.acmsoft.checkgo.entity.ArrivalLocationDay;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ArrivalLocationDayMapper {
    @Mapping(target = "dayOfWeek", source = "arrivalLocationDayRequestDto.day")
    @Mapping(target = "enabled", source = "arrivalLocationDayRequestDto.enabled")
    @Mapping(target = "dayId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "arrivalLocation", ignore = true)
    ArrivalLocationDay toEntity(ArrivalLocationDayRequestDTO arrivalLocationDayRequestDto);

    @Mapping(target = "day", source = "arrivalLocationDay.dayOfWeek")
    @Mapping(target = "enabled", source = "arrivalLocationDay.enabled")
    @Mapping(target = "intervalos", ignore = true)
    ArrivalLocationDayResponseDTO toDto(ArrivalLocationDay arrivalLocationDay);

    List<ArrivalLocationDayResponseDTO> toDtoList(List<ArrivalLocationDay> arrivalLocationDays);
}
