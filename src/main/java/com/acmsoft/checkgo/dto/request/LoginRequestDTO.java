package com.acmsoft.checkgo.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDTO {
    @NotBlank(message = "El nombre de usuario es obligatorio.")
    private String userName;
    @NotBlank(message = "La contraseña es obligatorio.")
    private String password;
}
