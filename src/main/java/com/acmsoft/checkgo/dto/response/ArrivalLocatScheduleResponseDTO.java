package com.acmsoft.checkgo.dto.response;

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
public class ArrivalLocatScheduleResponseDTO {
    private String publicId;
    private Day day;
    private LocalTime startTime; //no obligatorio
    private LocalTime endTime; //no obligatorio
    private boolean active;

}
