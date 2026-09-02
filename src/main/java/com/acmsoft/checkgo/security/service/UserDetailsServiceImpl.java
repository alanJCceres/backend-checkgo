package com.acmsoft.checkgo.security.service;

import com.acmsoft.checkgo.entity.User;
import com.acmsoft.checkgo.repository.UserRepository;
import com.acmsoft.checkgo.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String publicId){
        UUID uuid;
        try {
            uuid = UUID.fromString(publicId);
        } catch (IllegalArgumentException e) {
            throw new UsernameNotFoundException("Formato de publicId inválido: " + publicId);
        }
        User getUser = userRepository.findByPublicId(uuid)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + uuid));

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(getUser.getRol().toString());
        return new CustomUserDetails(
                getUser.getUserName(),
                getUser.getUserPassword(),
                getUser.getPublicId(),
                Collections.singletonList(authority)
        );
    }
}
