package com.przemo.rentcar.configurations.security;

import com.przemo.rentcar.services.AdministrationService;
import com.przemo.rentcar.users.Administration;
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
                                        .findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User 404"));
        return new UserPrincipale(administration);
    }


}