package com.acmsoft.checkgo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "plan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long planId;
    @Column(name = "public_id", insertable = false, updatable = false)
    @Generated
    private UUID publicId;
    @Column(name = "name_plan")
    private String namePlan;
    @Column(name = "max_users")
    private int maxUsers;
}
