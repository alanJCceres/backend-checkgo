package com.acmsoft.checkgo.controller;

import com.acmsoft.checkgo.dto.request.DeviceUpdateRequestDTO;
import com.acmsoft.checkgo.dto.response.UtcTimeResponse;
import com.acmsoft.checkgo.security.CustomUserDetails;
import com.acmsoft.checkgo.service.Implement.UserService;
import com.acmsoft.checkgo.service.TimeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shared")
public class SharedController {
    private final UserService userService;
    private final TimeService timeService;

    @PutMapping("/device")
    public ResponseEntity<Void> saveAndroidId(@Valid @RequestBody DeviceUpdateRequestDTO deviceUpdateRequestDTO,@AuthenticationPrincipal CustomUserDetails userDetails){
        userService.updateAndroidId(deviceUpdateRequestDTO,userDetails);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/time")
    public ResponseEntity<UtcTimeResponse> getUtcTime() {
        return ResponseEntity.ok().cacheControl(CacheControl.noStore()).body(timeService.getCurrentUtcTime());
    }
}
