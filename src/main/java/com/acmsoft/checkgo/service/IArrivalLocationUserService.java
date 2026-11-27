package com.acmsoft.checkgo.service;

import com.acmsoft.checkgo.dto.response.UserResponseDTO;
import com.acmsoft.checkgo.entity.ArrivalLocation;
import com.acmsoft.checkgo.entity.ArrivalLocationUser;
import com.acmsoft.checkgo.entity.User;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IArrivalLocationUserService {
    void saveArrivalLocationUsers(List<User> users, ArrivalLocation arrivalLocation);
    List<ArrivalLocation> getAllArrivalLocationForUser(UUID publicIdUser);
    List<ArrivalLocationUser> getAllArrivalLocationUser(List<ArrivalLocation> locations);
    Map<UUID, List<UserResponseDTO>> usersByLocation(List<ArrivalLocation> locations);
}
