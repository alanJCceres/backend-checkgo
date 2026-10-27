package com.acmsoft.checkgo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "arrival_location_schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArrivalLocationSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long scheduleId;
    @Column(name = "public_id", insertable = false, updatable = false)
    @Generated
    private UUID publicId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_location_id", nullable = false)
    private ArrivalLocation arrivalLocation;
    @JoinColumn(name = "day", nullable = false)
    private String day;
    @Column(name = "start_time", nullable = true)
    private LocalTime startTime;
    @Column(name = "end_time", nullable = true)
    private LocalTime endTime;
    @JoinColumn(name = "active", nullable = false)
    private boolean active;
    @ManyToMany(mappedBy = "arrivalLocationSchedules")
    private Set<ArrivalLocation> arrivalLocations = new HashSet<>();
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @CreationTimestamp
    @Column(name = "updated_at", nullable = false, updatable = false)
    private LocalDateTime updatedAt;
}
