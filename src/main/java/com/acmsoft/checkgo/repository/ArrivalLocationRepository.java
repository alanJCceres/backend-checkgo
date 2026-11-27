package com.acmsoft.checkgo.repository;

import com.acmsoft.checkgo.entity.ArrivalLocation;
import com.acmsoft.checkgo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ArrivalLocationRepository extends JpaRepository<ArrivalLocation,Long> {
   List<ArrivalLocation> findByCreatedBy_publicId(UUID publicIdAdmin);
}
