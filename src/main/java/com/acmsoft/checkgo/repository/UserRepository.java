package com.acmsoft.checkgo.repository;

import com.acmsoft.checkgo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
Optional<User> findByPublicId(UUID publicId);
boolean existsByUserName(String nombreUsuario);
boolean existsByEmail(String email);
boolean existsByImei(String imei);
}
