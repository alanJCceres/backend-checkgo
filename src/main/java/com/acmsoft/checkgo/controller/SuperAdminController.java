package com.acmsoft.checkgo.controller;

import com.acmsoft.checkgo.dto.request.UserUpdateCredentialsRequest;
import com.acmsoft.checkgo.dto.response.UserResponseDTO;
import com.acmsoft.checkgo.security.CustomUserDetails;
import com.acmsoft.checkgo.service.Implement.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/super_admin")
public class SuperAdminController {
    private final UserService userService;
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(@AuthenticationPrincipal CustomUserDetails userDetails){
        return ResponseEntity.ok(userService.getAllUsers(userDetails));
    }
    @PatchMapping("/users/{publicIdUser}/credentials")
    public ResponseEntity<Void> updateCredentials(
            @PathVariable UUID publicIdUser,
            @Valid @RequestBody UserUpdateCredentialsRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        userService.updateCredentialsUser(publicIdUser,request,userDetails);

        return ResponseEntity.noContent().build();
    }
}
