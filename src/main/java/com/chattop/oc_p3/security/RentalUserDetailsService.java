package com.chattop.oc_p3.security;

import com.chattop.oc_p3.entity.AppUser;
import com.chattop.oc_p3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RentalUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(this::createSecurityUser)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private User createSecurityUser(AppUser appUser) {
        return new User(appUser.getEmail(), appUser.getPassword(), List.of(new SimpleGrantedAuthority("USER")));
    }
}
