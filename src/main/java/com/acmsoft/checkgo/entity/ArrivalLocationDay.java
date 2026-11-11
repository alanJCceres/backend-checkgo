package com.acmsoft.checkgo.entity;

import com.acmsoft.checkgo.enums.Day;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "arrival_location_day")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArrivalLocationDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "day_id")
    private Long dayId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private ArrivalLocation arrivalLocation;
    @Column(name= "day_of_week", nullable = false)
    private Day dayOfWeek;
    @Column(name= "enabled", nullable = false)
    private boolean enabled;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

}
