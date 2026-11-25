package com.acmsoft.checkgo.service;

import com.acmsoft.checkgo.entity.ArrivalLocation;
import com.acmsoft.checkgo.entity.User;

import java.util.List;
import java.util.UUID;

public interface IArrivalLocationUserService {
    void saveArrivalLocationUsers(List<User> users, ArrivalLocation arrivalLocation);
    List<ArrivalLocation> getAllArrivalLocationForUser(UUID publicIdUser);
}
