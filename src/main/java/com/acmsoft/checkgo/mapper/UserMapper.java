package com.acmsoft.checkgo.mapper;

import com.acmsoft.checkgo.dto.request.UserCreateRequestDTO;
import com.acmsoft.checkgo.entity.Plan;
import com.acmsoft.checkgo.entity.User;
import com.acmsoft.checkgo.enums.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "plan", source = "plan")
    @Mapping(target= "userPassword", source="userRequest.password")
    @Mapping(target="rol", source="userRequest.rol")
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "publicId", ignore = true)
    User toUser(UserCreateRequestDTO userRequest, Plan plan);
}
