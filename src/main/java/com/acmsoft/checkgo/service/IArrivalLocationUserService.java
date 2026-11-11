package com.acmsoft.checkgo.service;

import com.acmsoft.checkgo.entity.ArrivalLocation;
import com.acmsoft.checkgo.entity.User;

import java.util.List;

public interface IArrivalLocationUserService {
    void saveArrivalLocationUsers(List<User> users, ArrivalLocation arrivalLocation);
}
