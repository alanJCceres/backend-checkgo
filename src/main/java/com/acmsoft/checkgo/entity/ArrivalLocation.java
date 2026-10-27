package com.acmsoft.checkgo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "arrival_location")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArrivalLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long locationId;
    @Column(name = "public_id", insertable = false, updatable = false)
    @Generated
    private UUID publicId;
    @Column(name= "location_name", nullable = false)
    private String locationName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdBy", nullable = false)
    private User createdBy;
    @Column(name= "address", nullable = false)
    private String address;
    @Column(name="location",columnDefinition = "geography(Point,4326)")
    private Point location;
    @Column(name= "active", nullable = false)
    private boolean active;
    @Column(name= "arrival_only", nullable = false)
    private boolean arrivalOnly;
    @Column(name= "exit_only", nullable = false)
    private boolean exitOnly;
    @Column(name= "arrival_and_departure", nullable = false)
    private boolean arrivalAndDeparture;
    @Column(name= "tolerance_minutes", nullable = false)
    private int toleranceMinutes;
    @Column(name= "radio_aceptable_meters", nullable = false)
    private int radioAceptableMeters;
    @ManyToMany
    @JoinTable(
            name = "arrival_location_schedules",
            joinColumns = @JoinColumn(name = "location_id"),
            inverseJoinColumns = @JoinColumn(name = "schedule_id")
    )
    private Set<ArrivalLocationSchedule> arrivalLocationSchedules = new HashSet<>();
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @CreationTimestamp
    @Column(name = "updated_at", nullable = false, updatable = false)
    private LocalDateTime updatedAt;
}
