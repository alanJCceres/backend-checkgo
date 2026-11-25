package com.acmsoft.checkgo.repository;

import com.acmsoft.checkgo.entity.ArrivalLocation;
import com.acmsoft.checkgo.entity.ArrivalLocationSchedule;
import com.acmsoft.checkgo.entity.ArrivalLocationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface ArrivalLocationUserRepository extends JpaRepository<ArrivalLocationUser,Long> {
    @Query("""
        SELECT alu.arrivalLocation
        FROM ArrivalLocationUser alu
        WHERE alu.user.publicId = :publicId
    """)
    List<ArrivalLocation> findAllArrivalLocationsByUserPublicId(
            @Param("publicId") UUID publicId
    );
}
