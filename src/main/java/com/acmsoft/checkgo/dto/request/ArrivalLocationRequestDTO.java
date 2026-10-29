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
    @NotNull(message = "La latitud es obligatorio.")
    @DecimalMin("-90") @DecimalMax("90")
    private Double latitude;
    @NotNull(message = "La longitud es obligatorio")
    @DecimalMin("-180") @DecimalMax("180")
    private Double longitude;
    @NotNull(message = "el campo arrival only es obligatorio.")
    private Boolean arrivalOnly=false;
    @NotNull(message = "el campo exit only es obligatorio.")
    private Boolean exitOnly=false;
    @NotNull(message = "el campo arrival and departure es obligatorio.")
    private Boolean arrivalAndDeparture=false;
    @Max(value = 30,message = "La tolerancia no debe ser mas de 30 minutos")
    @Min(value= 0, message = "La tolerancia no puede ser negativo.")
    private int tolerance;
    @NotNull(message = "El radio de la ubicación es obligatorio.")
    @Min(value= 1, message = "El radio debe ser minimo 1 metro.")
    @Max(value = 1000,message = "El radio no puede ser mas de 1000 metros.")
    private int radio;
    @NotEmpty(message = "Debe tener asignado al menos un horario.")
    private List<@NotBlank(message = "El horario no puede estar vacío.") String> locationSchedules;
    @NotEmpty(message = "Debe tener asignado al menos un usuario.")
    private List<@NotBlank(message = "El usuario no puede estar vacío.") String> assignedUsers;
}
