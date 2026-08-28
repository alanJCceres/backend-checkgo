package com.acmsoft.checkgo.service;

import com.acmsoft.checkgo.dto.request.UserCreateRequestDTO;
import com.acmsoft.checkgo.entity.User;

import java.util.UUID;

public interface IUserService {
    User saveUser(UserCreateRequestDTO requestUser);
    User findUserByPublicId(UUID publicId);
}
