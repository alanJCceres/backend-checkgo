package com.acmsoft.checkgo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "Arrival_location_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArrivalLocationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_location_id", nullable = false)
    private ArrivalLocation arrivalLocation;
    @CreationTimestamp
    @Column(name = "assignment_date", nullable = false, updatable = false)
    private LocalDateTime assignmentDate;
}
