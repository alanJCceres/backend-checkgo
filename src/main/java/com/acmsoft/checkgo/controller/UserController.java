package com.acmsoft.checkgo.controller;

import com.acmsoft.checkgo.dto.response.ArrivalLocationResponseForUserDTO;
import com.acmsoft.checkgo.security.CustomUserDetails;
import com.acmsoft.checkgo.service.Implement.ArrivalLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final ArrivalLocationService arrivalLocationService;

    @GetMapping("/arrivalLocation")
    public ResponseEntity<List<ArrivalLocationResponseForUserDTO>> getAllArrivalLocations(@AuthenticationPrincipal CustomUserDetails userDetails){
        //return ResponseEntity.ok(userService.getAllUsers(userDetails));
        return ResponseEntity.ok(arrivalLocationService.getAllArrivalLocationForUser(userDetails));
    }
}
