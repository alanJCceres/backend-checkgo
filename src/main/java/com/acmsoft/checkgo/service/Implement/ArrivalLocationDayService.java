package com.acmsoft.checkgo.service.Implement;

import com.acmsoft.checkgo.dto.request.ArrivalLocationDayRequestDto;
import com.acmsoft.checkgo.entity.ArrivalLocation;
import com.acmsoft.checkgo.entity.ArrivalLocationDay;
import com.acmsoft.checkgo.mapper.ArrivalLocationDayMapper;
import com.acmsoft.checkgo.repository.ArrivalLocationDayRepository;
import com.acmsoft.checkgo.service.IArrivalLocationDayService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
