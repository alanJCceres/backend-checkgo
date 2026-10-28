package com.acmsoft.checkgo.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArrivalLocationRequestDTO {
    @NotBlank(message = "El nombre de la ubicación es obligatorio.")
    @Size(min = 3, max = 100, message = "El nombre de la ubicación debe tener entre 3 y 100 caracteres")
    private String locationName;
    @NotBlank(message = "El nombre de la dirección es obligatorio.")
    @Size(min = 3, max = 255, message = "El nombre de la dirección debe tener entre 3 y 255 caracteres")
    private String address;
    @NotBlank(message = "La latitud es obligatorio.")
    private String latitude;
    @NotBlank(message = "La longitud es obligatorio.")
    private String logitude;
    private boolean arrivalOnly;
    private boolean exitOnly;
    private boolean arrivalAndDeparture;
    @Max(value = 30,message = "la tolerancia no debe ser mas de 30 minutos")
    private int tolerance;
    @NotBlank(message = "El radio de la ubicación es obligatorio.")
    @Min(value= 1, message = "el radio debe ser minimo 1 metro.")
    private int radio;
    @NotEmpty(message = "Debe tener asignado al menos un horario.")
    private List<@NotBlank(message = "El horario no puede estar vacío.") String> locationSchedules;
    @NotEmpty(message = "Debe tener asignado al menos un usuario.")
    private List<@NotBlank(message = "El usuario no puede estar vacío.") String> assignedUsers;
}
