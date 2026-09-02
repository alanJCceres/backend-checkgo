package com.acmsoft.checkgo.controller;

import com.acmsoft.checkgo.dto.request.LoginRequestDTO;
import com.acmsoft.checkgo.dto.request.UserCreateRequestDTO;
import com.acmsoft.checkgo.dto.response.JwtResponseDTO;
import com.acmsoft.checkgo.entity.User;
import com.acmsoft.checkgo.service.Implement.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@Valid @RequestBody LoginRequestDTO request){
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserCreateRequestDTO userCreateRequestDTO, UriComponentsBuilder uriBuilder){
        User newUser = authService.registerUser(userCreateRequestDTO);
        URI uri = uriBuilder.path("/api/v1/User/{id}").buildAndExpand(newUser.getPublicId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}

