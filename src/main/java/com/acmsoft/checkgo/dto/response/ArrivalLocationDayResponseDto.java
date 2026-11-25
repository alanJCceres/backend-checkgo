package com.acmsoft.checkgo.dto.response;

import com.acmsoft.checkgo.enums.Day;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArrivalLocationDayResponseDto {
    private Day day;
    private boolean enabled;
    private List<ArrivalLocationScheduleResponseDto> intervalos;
}
