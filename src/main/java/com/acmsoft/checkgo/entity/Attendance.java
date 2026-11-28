package com.acmsoft.checkgo.entity;

import com.acmsoft.checkgo.enums.AttendanceType;
import com.acmsoft.checkgo.enums.Day;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "attendance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long attendanceId;
    @Column(name = "public_id", insertable = false, updatable = false)
    @Generated
    private UUID publicId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_location_id", nullable = false)
    private User arrivalLocation;
    @Column(name= "day", nullable = false)
    private Day day;
    @JoinColumn(name = "type_attendance", nullable = false)
    private AttendanceType attendanceType;
    @Column(name = "datetime_server_check", nullable = false, updatable = false)
    private Instant dateTimeServerCheck;
    @Column(name = "datetime_phone_check", nullable = false, updatable = false)
    private Instant dateTimePhoneCheck;
    @Column(name = "phone_time_zone", nullable = false)
    private String phoneTimeZone;
    @Column(name="location_check",columnDefinition = "geography(Point,4326)")
    private Point locationCheck;
    @Column(name= "address")
    private String address;
    @JoinColumn(name = "photography_route")
    private String photographyRoute;
    @JoinColumn(name = "android_id", nullable = false)
    private String androidId;
    @JoinColumn(name = "difference_android_id", nullable = false)
    private boolean differentAndroidId;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

}
