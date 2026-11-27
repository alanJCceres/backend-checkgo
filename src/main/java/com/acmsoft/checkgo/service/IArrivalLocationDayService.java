package com.acmsoft.checkgo.service;

import com.acmsoft.checkgo.dto.request.ArrivalLocationDayRequestDto;
import com.acmsoft.checkgo.dto.response.ArrivalLocationDayResponseDTO;
import com.acmsoft.checkgo.dto.response.ArrivalLocationScheduleResponseDTO;
import com.acmsoft.checkgo.entity.ArrivalLocation;
import com.acmsoft.checkgo.entity.ArrivalLocationDay;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IArrivalLocationDayService {
    List<ArrivalLocationDay> savedDays(List<ArrivalLocationDayRequestDto> request, ArrivalLocation arrivalLocation);
    List<ArrivalLocationDay> getDaysEnabled(List<ArrivalLocation> arrivalLocations);
    Map<UUID, List<ArrivalLocationDayResponseDTO>> daysByLocation(List<ArrivalLocationDay> days, Map<Long, List<ArrivalLocationScheduleResponseDTO>> schedulesByDayId);
}
