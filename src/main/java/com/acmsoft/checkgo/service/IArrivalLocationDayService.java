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
    Map<UUID, List<ArrivalLocationDayResponseDTO>> daysByLocation(List<ArrivalLocation> arrivalLocations);
}
