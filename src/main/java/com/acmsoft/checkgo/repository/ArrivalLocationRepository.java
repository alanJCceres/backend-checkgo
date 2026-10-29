package com.acmsoft.checkgo.repository;

import com.acmsoft.checkgo.entity.ArrivalLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrivalLocationRepository extends JpaRepository<ArrivalLocation,Long> {
}
