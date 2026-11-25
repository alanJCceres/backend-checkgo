package com.acmsoft.checkgo.mapper;

import com.acmsoft.checkgo.dto.request.ArrivalLocationScheduleRequestDto;
import com.acmsoft.checkgo.dto.response.ArrivalLocationScheduleResponseDto;
import com.acmsoft.checkgo.entity.ArrivalLocationSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ArrivalLocationScheduleMapper {
    @Mapping(target = "startTime", source = "arrivalLocationScheduleRequestDto.startTime")
    @Mapping(target = "endTime", source = "arrivalLocationScheduleRequestDto.endTime")
    @Mapping(target = "scheduleId", ignore = true)
    @Mapping(target = "arrivalLocationDay", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    ArrivalLocationSchedule toEntity(ArrivalLocationScheduleRequestDto arrivalLocationScheduleRequestDto);

    @Mapping(target = "startTime", source = "arrivalLocationSchedule.startTime")
    @Mapping(target = "endTime", source = "arrivalLocationSchedule.endTime")
    ArrivalLocationScheduleResponseDto toDto(ArrivalLocationSchedule arrivalLocationSchedule);

    List<ArrivalLocationScheduleRequestDto> toDtoList(List<ArrivalLocationSchedule> arrivalLocationSchedules);
}
