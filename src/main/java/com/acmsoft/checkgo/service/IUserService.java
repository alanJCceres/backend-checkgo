package com.acmsoft.checkgo.service;

import com.acmsoft.checkgo.dto.request.UserCreateRequestDTO;
import com.acmsoft.checkgo.entity.User;

public interface IUserService {
    User saveUser(UserCreateRequestDTO requestUser);
}
