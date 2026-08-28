package com.acmsoft.checkgo.service;

import com.acmsoft.checkgo.entity.Plan;

import java.util.UUID;

public interface IPlanService {
    Plan findPlanByPublicId(UUID publicId);
}
