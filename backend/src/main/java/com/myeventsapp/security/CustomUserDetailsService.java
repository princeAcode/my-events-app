package com.myeventsapp.security;

import com.myeventsapp.user.UserEntity;
import com.myeventsapp.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
  private final UserRepository users;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserEntity u = users.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Not found"));
    return User.withUsername(u.getEmail()).password(u.getPasswordHash()).roles(u.getRole().name()).build();
  }
}
