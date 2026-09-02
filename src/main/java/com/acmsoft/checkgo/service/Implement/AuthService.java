package com.acmsoft.checkgo.service.Implement;

import com.acmsoft.checkgo.dto.request.LoginRequestDTO;
import com.acmsoft.checkgo.dto.request.UserCreateRequestDTO;
import com.acmsoft.checkgo.dto.response.JwtResponseDTO;
import com.acmsoft.checkgo.entity.User;
import com.acmsoft.checkgo.security.service.JwtService;
import com.acmsoft.checkgo.service.IAuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Transactional
    public User registerUser(UserCreateRequestDTO userRequest) {
        return userService.saveUser(userRequest);
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
}
