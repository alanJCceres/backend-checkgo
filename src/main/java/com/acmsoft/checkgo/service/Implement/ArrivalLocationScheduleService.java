package com.acmsoft.checkgo.service.Implement;

import com.acmsoft.checkgo.dto.response.ArrivalLocatScheduleResponseDTO;
import com.acmsoft.checkgo.entity.ArrivalLocationSchedule;
import com.acmsoft.checkgo.exception.BadRequestException;
import com.acmsoft.checkgo.mapper.ArrivalLocationScheduleMapper;
import com.acmsoft.checkgo.repository.ArrivalLocationScheduleRepository;
import com.acmsoft.checkgo.security.CustomUserDetails;
import com.acmsoft.checkgo.service.IArrivalLocationScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArrivalLocationScheduleService implements IArrivalLocationScheduleService {
    private final ArrivalLocationScheduleRepository arrivalLocationScheduleRepository;
    private final ArrivalLocationScheduleMapper arrivalLocationScheduleMapper;

    public List<ArrivalLocatScheduleResponseDTO> getAllSchedules(CustomUserDetails userDetails){
        if(userDetails == null){
            throw new BadRequestException("No se puede listar los usuarios sin un access token o con un token expirado/invalido.");
        }
        List<ArrivalLocationSchedule> getSchedules = arrivalLocationScheduleRepository.findAllByCreatedBy_publicId(userDetails.getPublicId());
        return arrivalLocationScheduleMapper.toDTOList(getSchedules);
    }
}
