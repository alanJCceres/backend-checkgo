package com.acmsoft.checkgo.dto.request;

import com.acmsoft.checkgo.enums.Day;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArrivalLocationDayRequestDto {
    @NotEmpty(message = "Debe tener asignado un día de la semana.")
    private Day day;
    @NotNull(message = "el campo enabled es obligatorio.")
    private boolean enabled;
    private List<ArrivalLocationScheduleRequestDto> intervalos;
}
