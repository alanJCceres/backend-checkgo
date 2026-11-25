package com.acmsoft.checkgo.repository;

import com.acmsoft.checkgo.entity.ArrivalLocation;
import com.acmsoft.checkgo.entity.ArrivalLocationDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ArrivalLocationDayRepository extends JpaRepository<ArrivalLocationDay,Long> {
    List<ArrivalLocationDay> findByArrivalLocationInAndEnabledTrue(Collection<ArrivalLocation> locations);
}
