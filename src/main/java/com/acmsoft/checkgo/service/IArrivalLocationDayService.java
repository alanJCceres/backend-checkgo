package com.acmsoft.checkgo.service;

import com.acmsoft.checkgo.dto.request.ArrivalLocationDayRequestDto;
import com.acmsoft.checkgo.entity.ArrivalLocation;
import com.acmsoft.checkgo.entity.ArrivalLocationDay;

import java.util.List;

public interface IArrivalLocationDayService {
    List<ArrivalLocationDay> savedDays(List<ArrivalLocationDayRequestDto> request, ArrivalLocation arrivalLocation);
}
