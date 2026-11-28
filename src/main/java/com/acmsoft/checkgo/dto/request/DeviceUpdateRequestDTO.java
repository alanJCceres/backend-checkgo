package com.acmsoft.checkgo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceUpdateRequestDTO {
    @NotBlank(message = "el ID de android no puede estar vacio.")
    @Size(max = 20, message = "El ID de Android no debe tener más de 20 caracteres.")
    private String androidId;
}
