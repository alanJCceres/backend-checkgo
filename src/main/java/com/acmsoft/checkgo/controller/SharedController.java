package com.acmsoft.checkgo.controller;

import com.acmsoft.checkgo.dto.request.DeviceUpdateRequestDTO;
import com.acmsoft.checkgo.security.CustomUserDetails;
import com.acmsoft.checkgo.service.Implement.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shared")
public class SharedController {
    private final UserService userService;

    @PutMapping("/device")
    public ResponseEntity<Void> saveAndroidId(@Valid @RequestBody DeviceUpdateRequestDTO deviceUpdateRequestDTO,@AuthenticationPrincipal CustomUserDetails userDetails){
        userService.updateAndroidId(deviceUpdateRequestDTO,userDetails);
        return ResponseEntity.noContent().build();
    }

}
