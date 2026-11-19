package com.acmsoft.checkgo.service.Implement;

import com.acmsoft.checkgo.dto.request.ArrivalLocationDayRequestDto;
import com.acmsoft.checkgo.entity.ArrivalLocationDay;
import com.acmsoft.checkgo.entity.ArrivalLocationSchedule;
import com.acmsoft.checkgo.enums.Day;
import com.acmsoft.checkgo.mapper.ArrivalLocationScheduleMapper;
import com.acmsoft.checkgo.repository.ArrivalLocationScheduleRepository;
import com.acmsoft.checkgo.service.IArrivalLocationScheduleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArrivalLocationScheduleService implements IArrivalLocationScheduleService {
    private final ArrivalLocationScheduleRepository arrivalLocationScheduleRepository;
    private final ArrivalLocationScheduleMapper arrivalLocationScheduleMapper;

    @Transactional
    public void saveSchedulesDays(List<ArrivalLocationDayRequestDto> request, List<ArrivalLocationDay> savedDays){

        Map<Day, ArrivalLocationDay> daysMap = savedDays.stream()
                .collect(Collectors.toMap(
                        ArrivalLocationDay::getDayOfWeek,
                        Function.identity()
                ));

        List<ArrivalLocationSchedule> schedules = request.stream()
                .flatMap(dayDto -> dayDto.getIntervalos().stream()
                        .map(intervalDto -> {
                            ArrivalLocationSchedule schedule = arrivalLocationScheduleMapper.toEntity(intervalDto);
                            schedule.setArrivalLocationDay(daysMap.get(dayDto.getDay()));
                            return schedule;
                        })
                )
                .toList();
        arrivalLocationScheduleRepository.saveAll(schedules);
    }
}
