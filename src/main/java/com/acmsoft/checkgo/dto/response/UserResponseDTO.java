package com.acmsoft.checkgo.dto.response;

import com.acmsoft.checkgo.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    private UUID publicId;
    private String fullname;
    private String userName;
    private String email;
    private Rol rol;
    private boolean active;
    private String androidId;
    private UserResponseDTO superAdmin;
}
