package com.acmsoft.checkgo.dto.request;

import com.acmsoft.checkgo.enums.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateRequestDTO {
    @NotBlank(message = "El nombre completo es obligatorio.")
    @Size(min = 3, max = 100, message = "El nombre completo debe tener entre 3 y 100 caracteres")
    private String fullname;
    @Email
    private String email; //opcional para USER, obligatorio para SUPER ADMIN
    @NotBlank(message = "El nombre de usuario es obligatorio.")
    @Size(min = 3, max = 20, message = "El nombre de usuario debe tener entre 3 y 20 caracteres")
    private String userName;
    @NotBlank(message = "La contraseña es obligatorio.")
    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
    private String password;
    @NotNull(message = "El rol es obligatorio.")
    private Rol rol;
    private UUID planPublicId; //SOLO PARA SUPER ADMIN
    private UUID createdBy; //SOLO PARA USER
}
