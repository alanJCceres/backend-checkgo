package com.acmsoft.checkgo.repository;

import com.acmsoft.checkgo.entity.Plan;
import com.acmsoft.checkgo.enums.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PlanRepository extends JpaRepository<Plan,Long> {
Optional<Plan> findByPublicId(UUID publicId);
}
