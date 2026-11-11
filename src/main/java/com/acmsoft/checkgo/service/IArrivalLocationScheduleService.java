package com.acmsoft.checkgo.service;

import com.acmsoft.checkgo.dto.request.ArrivalLocationDayRequestDto;
import com.acmsoft.checkgo.entity.ArrivalLocationDay;

import java.util.List;

public interface IArrivalLocationScheduleService {
    void saveSchedulesDays(List<ArrivalLocationDayRequestDto> request, List<ArrivalLocationDay> savedDays);
}
