package com.acmsoft.checkgo.service.Implement;

import com.acmsoft.checkgo.dto.request.LoginRequestDTO;
import com.acmsoft.checkgo.dto.request.UserCreateRequestDTO;
import com.acmsoft.checkgo.dto.response.JwtResponseDTO;
import com.acmsoft.checkgo.entity.User;
import com.acmsoft.checkgo.enums.Rol;
import com.acmsoft.checkgo.exception.BadRequestException;
import com.acmsoft.checkgo.exception.InvalidTokenException;
import com.acmsoft.checkgo.security.CustomUserDetails;
import com.acmsoft.checkgo.security.service.JwtService;
import com.acmsoft.checkgo.security.service.UserDetailsServiceImpl;
import com.acmsoft.checkgo.service.IAuthService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    @Transactional
    public User registerUser(UserCreateRequestDTO userRequest, CustomUserDetails userDetails) {
        UUID publicId=null;
        if(userDetails != null){
            String role = userDetails.getAuthorities().stream()
                    .findFirst()
                    .map(GrantedAuthority::getAuthority)
                    .orElse(null);
            if(role.equals(Rol.USER.toString())){
                throw new BadRequestException("Para registrar un User debe tener permisos de SUPER ADMIN.");
            }
            if(userRequest.getRol() == Rol.SUPER_ADMIN){
                throw new BadRequestException("No se puede registrar un SUPER ADMIN con access token.");
            }
            publicId = userDetails.getPublicId();
        }

        if(userDetails == null && userRequest.getRol() == Rol.USER){
            throw new BadRequestException("No se puede registrar un USER sin un access token.");
        }

        return userService.saveUser(userRequest,publicId);
    }

    public JwtResponseDTO authenticate(LoginRequestDTO loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),
                        loginRequest.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String accessToken = jwtService.generateAccessToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        String useRole = userDetails.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("USER");

        return new JwtResponseDTO(accessToken,refreshToken,useRole);
    }

    public JwtResponseDTO refreshToken(String refreshToken){
        final String publicId;
        try {
            publicId = jwtService.extractPublicUserId(refreshToken); // extrae el "sub"
        } catch (ExpiredJwtException e) {
            throw new InvalidTokenException("Refresh token expirado");
        } catch (JwtException e) {
            throw new InvalidTokenException("Refresh token inválido");
        }

        UserDetails userDetails = userDetailsService.loadUserByPublicId(publicId);
        if (!jwtService.isTokenValid(refreshToken)) {
            throw new InvalidTokenException("Refresh token inválido o expirado");
        }
        String newAccessToken = jwtService.generateAccessToken(userDetails);
        String useRole = userDetails.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("USER");

        return new JwtResponseDTO(newAccessToken,refreshToken,useRole);
    }

    public boolean getFirstTimeLoginUser(CustomUserDetails userDetails){
        if(userDetails == null){
            throw new InvalidTokenException("Access token invalido o expirado.");
        }
        UUID publicId = userDetails.getPublicId();
        return userService.getFirstTimeLogin(publicId);
    }
}
