package com.acmsoft.checkgo.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;


@Getter
//Clase para poder obtener el publicId del user
public class CustomUserDetails extends org.springframework.security.core.userdetails.User{
    private final UUID publicId;
    public CustomUserDetails(String username, String password, UUID publicId, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.publicId = publicId;
    }
}
