package com.acmsoft.checkgo.repository;

import com.acmsoft.checkgo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
Optional<User> findByPublicId(UUID publicId);
Optional<User> findByUserName(String username);
boolean existsByUserName(String nombreUsuario);
boolean existsByEmail(String email);
@Query("SELECT u FROM User u JOIN FETCH u.superAdmin admin WHERE admin.publicId = :publicId")
List<User> findAllBySuperAdminPublicId(@Param("publicId") UUID publicId);
}
