package com.acmsoft.checkgo.service.Implement;

import com.acmsoft.checkgo.entity.Plan;
import com.acmsoft.checkgo.exception.ResourceNotFoundException;
import com.acmsoft.checkgo.repository.PlanRepository;
import com.acmsoft.checkgo.service.IPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlanService implements IPlanService {

    private final PlanRepository planRepository;
    public Plan findPlanByPublicId(UUID publicId){
        return planRepository.findByPublicId(publicId)
                .orElseThrow(() -> new ResourceNotFoundException("El plan con ID " + publicId + " no existe."));
    }
}
