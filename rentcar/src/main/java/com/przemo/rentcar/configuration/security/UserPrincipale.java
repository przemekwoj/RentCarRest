package com.przemo.rentcar.configuration.security;

import com.przemo.rentcar.entities.users.Administration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipale implements UserDetails
{

    private Administration administration;

    public UserPrincipale(Administration administration) {
        super();
        this.administration = administration;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = administration.getClass().getSimpleName(); //Employee or Supervisor
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return administration.getPassword();
    }

    @Override
    public String getUsername() {
        return administration.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
