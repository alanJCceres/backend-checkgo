package com.acmsoft.checkgo.service;

import com.acmsoft.checkgo.dto.request.ArrivalLocationRequestDTO;
import com.acmsoft.checkgo.entity.ArrivalLocation;
import com.acmsoft.checkgo.security.CustomUserDetails;

public interface IArrivalLocationService {
    ArrivalLocation saveArrivalLocation(ArrivalLocationRequestDTO arrivalLocationRequestDTO, CustomUserDetails userDetails);
}
