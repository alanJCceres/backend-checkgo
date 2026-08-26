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
    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String fullname;
    @Email
    private String email;
    @NotBlank(message = "El nombre de usuario es obligatorio.")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String userName;
    @NotBlank(message = "La contraseña es obligatorio.")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String password;
    @NotNull(message = "El rol es obligatorio.")
    private Rol rol;
    @NotNull(message = "El ID del plan es obligatorio.")
    private UUID planPublicId;
}
