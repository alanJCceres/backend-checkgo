package com.acmsoft.checkgo.dto.request;

import com.acmsoft.checkgo.enums.AttendanceType;
import com.acmsoft.checkgo.enums.Day;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceRequestDTO {
    @NotBlank(message = "El id de la ubicación es obligatoria.")
    private UUID arrivalLocationId;
    @NotEmpty(message = "El día es obligatorio.")
    private Day day;
    @NotEmpty(message = "El tipo de asistencia es obligatorio.")
    private AttendanceType attendanceType;
    @NotNull(message = "La latitud es obligatorio.")
    @DecimalMin(value = "-90.0", message = "La latitud debe ser mayor o igual a -90.")
    @DecimalMax(value = "90.0", message = "La latitud debe ser menor o igual a 90.")
    private Double latitude;
    @NotNull(message = "La longitud es obligatorio")
    @DecimalMin(value = "-180.0", message = "La longitud debe ser mayor o igual a -180.")
    @DecimalMax(value = "180.0", message = "La longitud debe ser menor o igual a 180.")
    private Double longitude;
    private String address;
    @NotNull(message = "La fotografía es obligatoria")
    private MultipartFile photo;
    @NotBlank(message = "El ID de android es obligatorio.")
    @Size(max = 20, message = "El ID de Android no debe tener más de 20 caracteres.")
    private String androidId;
    @NotBlank(message = "La fecha y hora del check son obligatorias.")
    private Instant dateTimePhoneCheck;
    @NotBlank(message = "La zona horaria del telefono es obligatorio.")
    @Size(max = 50, message = "La zona horaria no debe tener más de 50 caracteres.")
    private String phoneTimeZone;

}
