package com.acmsoft.checkgo.repository;

import com.acmsoft.checkgo.entity.ArrivalLocationSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface ArrivalLocationScheduleRepository extends JpaRepository<ArrivalLocationSchedule,Long> {
    List<ArrivalLocationSchedule> findAllByPublicIdIn(Collection<UUID> publicIds);
}
