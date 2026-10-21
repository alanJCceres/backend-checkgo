package com.acmsoft.checkgo.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import org.springframework.security.core.AuthenticationException;
import java.io.IOException;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        // Si el filtro marcó el token como expirado, lo usamos
        Object error = request.getAttribute("auth_error");
        String code = error != null ? error.toString() : "UNAUTHORIZED";
        String message = "TOKEN_EXPIRED".equals(code)
                ? "El token ha expirado"
                : "No autorizado";

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        Map<String, Object> body = Map.of(
                "status", 401,
                "error", code,
                "message", message
        );
        objectMapper.writeValue(response.getOutputStream(), body);
    }
}
