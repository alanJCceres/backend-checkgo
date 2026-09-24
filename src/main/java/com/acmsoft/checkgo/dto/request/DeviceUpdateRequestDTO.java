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
public class DeviceUpdateRequestDTO {
    @NotBlank(message = "el ID de android no puede estar vacio.")
    private String androidId;
}
