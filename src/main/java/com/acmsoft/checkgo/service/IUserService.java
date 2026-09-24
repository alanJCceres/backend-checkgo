package com.acmsoft.checkgo.service;

import com.acmsoft.checkgo.dto.request.DeviceUpdateRequestDTO;
import com.acmsoft.checkgo.dto.request.UserCreateRequestDTO;
import com.acmsoft.checkgo.entity.User;
import com.acmsoft.checkgo.security.CustomUserDetails;

import java.util.UUID;

public interface IUserService {
    User saveUser(UserCreateRequestDTO requestUser, UUID adminPublicId);
    User findUserByPublicId(UUID publicId);
    boolean getFirstTimeLogin(UUID publicIdUser);
    void updateAndroidId(DeviceUpdateRequestDTO deviceUpdateRequestDTO, CustomUserDetails userDetails);
}
