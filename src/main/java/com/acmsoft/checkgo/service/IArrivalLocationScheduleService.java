package com.acmsoft.checkgo.service;

import com.acmsoft.checkgo.dto.request.ArrivalLocationDayRequestDto;
import com.acmsoft.checkgo.dto.response.ArrivalLocationScheduleResponseDTO;
import com.acmsoft.checkgo.entity.ArrivalLocation;
import com.acmsoft.checkgo.entity.ArrivalLocationDay;
import com.acmsoft.checkgo.entity.ArrivalLocationSchedule;

import java.util.List;
import java.util.Map;

public interface IArrivalLocationScheduleService {
    void saveSchedulesDays(List<ArrivalLocationDayRequestDto> request, List<ArrivalLocationDay> savedDays);
    List<ArrivalLocationSchedule> getAllSchedulesDays(List<ArrivalLocation> arrivalLocations);
    Map<Long, List<ArrivalLocationScheduleResponseDTO>> schedulesByDayId(List<ArrivalLocation> arrivalLocations);
}
