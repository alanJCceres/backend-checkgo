package com.acmsoft.checkgo.security.service;

import com.acmsoft.checkgo.entity.User;
import com.acmsoft.checkgo.exception.ResourceNotFoundException;
import com.acmsoft.checkgo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String username){
        User getUser = userRepository.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el username: " + username));
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(getUser.getRol().toString());
        return new org.springframework.security.core.userdetails.User(
                getUser.getUserName(),
                getUser.getUserPassword(),
                Collections.singletonList(authority)
        );
    }
}
