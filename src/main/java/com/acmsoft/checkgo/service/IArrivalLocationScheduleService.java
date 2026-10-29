package com.acmsoft.checkgo.service;

import com.acmsoft.checkgo.dto.response.ArrivalLocatScheduleResponseDTO;
import com.acmsoft.checkgo.security.CustomUserDetails;

import java.util.List;

public interface IArrivalLocationScheduleService {
    List<ArrivalLocatScheduleResponseDTO> getAllSchedules(CustomUserDetails userDetails);
}
