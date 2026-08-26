package com.acmsoft.checkgo.entity;

import com.acmsoft.checkgo.enums.Rol;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "public_id", insertable = false, updatable = false)
    @Generated
    private UUID publicId;
    @Column(name= "fullname", nullable = false)
    private String fullname;
    @Column(name= "user_name", nullable = false)
    private String userName;
    @Column(name= "email", nullable = true)
    private String email;
    @Column(name= "user_password", nullable = false)
    private String userPassword;
    @Enumerated(EnumType.STRING)
    @Column(name= "rol", nullable = false)
    private Rol rol;
    @Column(name= "active", nullable = false)
    private boolean active = true;
    @Column(name= "imei", nullable = true)
    private String imei;
    @Column(name= "first_time_login", nullable = false)
    private boolean firstTimeLogin;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by", nullable = true)
    private User updatedBy;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "super_admin_id", nullable = true)
    private User superAdmin;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = true)
    private Plan plan;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @CreationTimestamp
    @Column(name = "updated_at", nullable = false, updatable = false)
    private LocalDateTime updatedAt;
}
