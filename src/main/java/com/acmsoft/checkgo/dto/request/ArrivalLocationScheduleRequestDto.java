package com.acmsoft.checkgo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArrivalLocationScheduleRequestDto {
    private LocalTime startTime;
    private LocalTime endTime;
}
