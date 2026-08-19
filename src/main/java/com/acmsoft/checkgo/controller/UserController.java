package com.acmsoft.checkgo.controller;

import com.acmsoft.checkgo.dto.request.UserCreateRequestDTO;
import com.acmsoft.checkgo.entity.User;
import com.acmsoft.checkgo.service.IUserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/User")
public class UserController {
    private final IUserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> createUser(@Valid UserCreateRequestDTO userCreateRequestDTO, UriComponentsBuilder uriBuilder){
        User newUser = userService.saveUser(userCreateRequestDTO);
        URI uri = uriBuilder.path("/api/v1/User/{id}").buildAndExpand(newUser.getPublicId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
