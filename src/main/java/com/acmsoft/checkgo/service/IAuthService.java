package com.acmsoft.checkgo.service;

import com.acmsoft.checkgo.dto.request.LoginRequestDTO;
import com.acmsoft.checkgo.dto.request.UserCreateRequestDTO;
import com.acmsoft.checkgo.dto.response.JwtResponseDTO;
import com.acmsoft.checkgo.entity.User;

public interface IAuthService {
    User registerUser(UserCreateRequestDTO requestUser);
    JwtResponseDTO authenticate(LoginRequestDTO loginRequest);
}
