package com.acmsoft.checkgo.service;

import com.acmsoft.checkgo.dto.request.ArrivalLocationRequestDTO;
import com.acmsoft.checkgo.dto.response.ArrivalLocationResponseForAdminDTO;
import com.acmsoft.checkgo.dto.response.ArrivalLocationResponseForUserDTO;
import com.acmsoft.checkgo.entity.ArrivalLocation;
import com.acmsoft.checkgo.security.CustomUserDetails;

import java.util.List;

public interface IArrivalLocationService {
    ArrivalLocation saveArrivalLocation(ArrivalLocationRequestDTO arrivalLocationRequestDTO, CustomUserDetails userDetails);
    List<ArrivalLocationResponseForUserDTO> getAllArrivalLocationForUser(CustomUserDetails userDetails);
    List<ArrivalLocationResponseForAdminDTO> getAllArrivalLocationForAdmin(CustomUserDetails userDetails);
}
