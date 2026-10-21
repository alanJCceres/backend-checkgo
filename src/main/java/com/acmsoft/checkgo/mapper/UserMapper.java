package com.acmsoft.checkgo.mapper;

import com.acmsoft.checkgo.dto.request.UserCreateRequestDTO;
import com.acmsoft.checkgo.dto.response.UserResponseDTO;
import com.acmsoft.checkgo.entity.Plan;
import com.acmsoft.checkgo.entity.User;
import com.acmsoft.checkgo.enums.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {
    @Mapping(target = "fullname", source = "userRequest.fullname")
    @Mapping(target = "userName", source = "userRequest.userName")
    @Mapping(target = "email", source = "userRequest.email")
    @Mapping(target = "userPassword", source = "userRequest.password")
    @Mapping(target = "rol", source = "userRequest.rol")
    @Mapping(target = "plan", source = "plan")
    @Mapping(target = "superAdmin", source = "createdBy")
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "publicId", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "androidId", ignore = true)
    @Mapping(target = "firstTimeLogin", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toUser(UserCreateRequestDTO userRequest, Plan plan,User createdBy);

    @Mapping(target = "fullname", source = "user.fullname")
    @Mapping(target = "userName", source = "user.userName")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "rol", source = "user.rol")
    @Mapping(target = "superAdmin", source = "user.superAdmin")
    @Mapping(target = "publicId", source = "user.publicId")
    @Mapping(target = "active", source = "user.active")
    @Mapping(target = "androidId", source = "user.androidId")
    UserResponseDTO toResponse(User user);

    List<UserResponseDTO> toResponseList(List<User> users);
}
