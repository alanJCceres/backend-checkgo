package com.acmsoft.checkgo.dto.request;

import com.acmsoft.checkgo.enums.Day;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArrivalLocatScheduleReqDTO {
    @NotNull(message = "El día es obligatorio.")
    private Day day;
    private LocalTime startTime;
    private LocalTime endTime;

}
