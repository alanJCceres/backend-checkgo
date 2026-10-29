package com.acmsoft.checkgo.mapper;

import com.acmsoft.checkgo.dto.response.ArrivalLocatScheduleResponseDTO;
import com.acmsoft.checkgo.entity.ArrivalLocationSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ArrivalLocationScheduleMapper {
    @Mapping(target = "day", source = "arrivalLocationSchedule.day")
    @Mapping(target = "startTime", source = "arrivalLocationSchedule.startTime")
    @Mapping(target = "endTime", source = "arrivalLocationSchedule.endTime")
    @Mapping(target = "active", source = "arrivalLocationSchedule.active")
    ArrivalLocatScheduleResponseDTO toDTO(ArrivalLocationSchedule arrivalLocationSchedule);

    List<ArrivalLocatScheduleResponseDTO> toDTOList(List<ArrivalLocationSchedule> locationSchedules);
}
