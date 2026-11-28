package com.acmsoft.checkgo.service;

import com.acmsoft.checkgo.dto.response.UtcTimeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TimeService {
    private final Clock clock;

    public UtcTimeResponse getCurrentUtcTime() {
        Instant now = Instant.now(clock);
        return new UtcTimeResponse(now.toString(), now.toEpochMilli());
    }
}
