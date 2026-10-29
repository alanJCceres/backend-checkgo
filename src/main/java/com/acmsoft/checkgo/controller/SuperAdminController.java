package com.acmsoft.checkgo.controller;

import com.acmsoft.checkgo.dto.request.ArrivalLocationRequestDTO;
import com.acmsoft.checkgo.dto.request.UserCreateRequestDTO;
import com.acmsoft.checkgo.dto.request.UserUpdateCredentialsRequest;
import com.acmsoft.checkgo.dto.response.ArrivalLocatScheduleResponseDTO;
import com.acmsoft.checkgo.dto.response.UserResponseDTO;
import com.acmsoft.checkgo.entity.ArrivalLocation;
import com.acmsoft.checkgo.security.CustomUserDetails;
import com.acmsoft.checkgo.service.Implement.ArrivalLocationScheduleService;
import com.acmsoft.checkgo.service.Implement.ArrivalLocationService;
import com.acmsoft.checkgo.service.Implement.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/super_admin")
public class SuperAdminController {
    private final UserService userService;
    private final ArrivalLocationService arrivalLocationService;
    private final ArrivalLocationScheduleService arrivalLocationScheduleService;

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

    @PostMapping("/arrivalLocation")
    public ResponseEntity<Void> createArrivalLocation(@Valid @RequestBody ArrivalLocationRequestDTO arrivalLocationRequestDTO,
                                                      UriComponentsBuilder uriBuilder,
                                                      @AuthenticationPrincipal CustomUserDetails userDetails){

        ArrivalLocation newArrivalLocation = arrivalLocationService.saveArrivalLocation(arrivalLocationRequestDTO,userDetails);
        URI uri = uriBuilder.path("/api/v1/super_admin/arrivalLocation/{id}").buildAndExpand(newArrivalLocation.getPublicId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @GetMapping("/arrivalLocation/schedules")
    public ResponseEntity<List<ArrivalLocatScheduleResponseDTO>> getAllSchedules(@AuthenticationPrincipal CustomUserDetails userDetails){
        return ResponseEntity.ok(arrivalLocationScheduleService.getAllSchedules(userDetails));
    }
}
