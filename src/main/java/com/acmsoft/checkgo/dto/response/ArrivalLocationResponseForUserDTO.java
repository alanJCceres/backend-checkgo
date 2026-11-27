package com.acmsoft.checkgo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArrivalLocationResponseForUserDTO {
    private UUID publicId;
    private String locationName;
    private String address;
    private Double latitude;
    private Double longitude;
    private Boolean arrivalOnly;
    private Boolean exitOnly;
    private Boolean arrivalAndDeparture;
    private int tolerance;
    private int radio;
    private String createdBy;
    private List<ArrivalLocationDayResponseDTO> locationSchedules;
}
