package com.przemo.rentcar.configuration.security;

import com.przemo.rentcar.services.AdministrationService;
import com.przemo.rentcar.entities.users.Administration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService
{
    @Autowired
    private AdministrationService administrationService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Administration administration = administrationService
                                        .findByEmail(email);
        return new UserPrincipale(administration);
    }


}