package com.acmsoft.checkgo.controller;

import com.acmsoft.checkgo.dto.response.UserResponseDTO;
import com.acmsoft.checkgo.security.CustomUserDetails;
import com.acmsoft.checkgo.service.Implement.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/super_admin")
public class SuperAdminController {
    private final UserService userService;
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(@AuthenticationPrincipal CustomUserDetails userDetails){
        return ResponseEntity.ok(userService.getAllUsers(userDetails));
    }
}
