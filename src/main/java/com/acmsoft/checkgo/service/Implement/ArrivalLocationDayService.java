package com.acmsoft.checkgo.service.Implement;

import com.acmsoft.checkgo.dto.request.ArrivalLocationDayRequestDto;
import com.acmsoft.checkgo.dto.response.ArrivalLocationDayResponseDTO;
import com.acmsoft.checkgo.dto.response.ArrivalLocationScheduleResponseDTO;
import com.acmsoft.checkgo.entity.ArrivalLocation;
import com.acmsoft.checkgo.entity.ArrivalLocationDay;
import com.acmsoft.checkgo.mapper.ArrivalLocationDayMapper;
import com.acmsoft.checkgo.repository.ArrivalLocationDayRepository;
import com.acmsoft.checkgo.service.IArrivalLocationDayService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArrivalLocationDayService implements IArrivalLocationDayService {
    private final ArrivalLocationDayRepository arrivalLocationDayRepository;
    private final ArrivalLocationDayMapper arrivalLocationDayMapper;

    @Transactional
    public List<ArrivalLocationDay> savedDays(List<ArrivalLocationDayRequestDto> request, ArrivalLocation arrivalLocation){
        List<ArrivalLocationDay> days = request.stream()
                .map(dayDto -> {
                    ArrivalLocationDay day = arrivalLocationDayMapper.toEntity(dayDto);
                    day.setArrivalLocation(arrivalLocation);
                    return day;
                })
                .toList();
        return arrivalLocationDayRepository.saveAll(days);
    }

    public List<ArrivalLocationDay> getDaysEnabled(List<ArrivalLocation> arrivalLocations){
        return arrivalLocationDayRepository.findByArrivalLocationInAndEnabledTrue(arrivalLocations);
    }

    public Map<UUID, List<ArrivalLocationDayResponseDTO>> daysByLocation(List<ArrivalLocationDay> days, Map<Long, List<ArrivalLocationScheduleResponseDTO>> schedulesByDayId){
        return days.stream()
                .sorted(Comparator.comparing(ArrivalLocationDay::getDayOfWeek))
                .collect(Collectors.groupingBy(
                        d -> d.getArrivalLocation().getPublicId(),
                        Collectors.mapping(d -> {
                            ArrivalLocationDayResponseDTO dto = arrivalLocationDayMapper.toDto(d);
                            dto.setIntervalos(schedulesByDayId.getOrDefault(d.getDayId(), List.of()));
                            return dto;
                        }, Collectors.toList())
                ));
    }
}
