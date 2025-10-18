package com.myeventsapp.user.service;

import com.myeventsapp.security.JwtUtil;
import com.myeventsapp.user.*;
import com.myeventsapp.user.dto.*;
import java.time.Instant;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository users;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authManager;
  private final JwtUtil jwtUtil;

  public void signup(SignupRequest req) {
    if (users.findByEmail(req.getEmail()).isPresent()) throw new IllegalArgumentException("Email already in use");
    UserEntity u = UserEntity.builder()
        .email(req.getEmail())
        .passwordHash(passwordEncoder.encode(req.getPassword()))
        .displayName(req.getDisplayName())
        .role(Role.USER)
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();
    users.save(u);
  }

  public TokenResponse login(LoginRequest req) {
    Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(auth);
    UserEntity u = users.findByEmail(req.getEmail()).orElseThrow();
    String access = jwtUtil.generateAccessToken(u.getEmail(), Map.of("role", u.getRole().name(), "uid", u.getId()));
    String refresh = jwtUtil.generateRefreshToken(u.getEmail(), Map.of("uid", u.getId()));
    return new TokenResponse(access, refresh);
  }

  public TokenResponse refresh(String refreshToken) {
    var claims = jwtUtil.parse(refreshToken);
    String email = claims.getSubject();
    UserEntity u = users.findByEmail(email).orElseThrow();
    String access = jwtUtil.generateAccessToken(u.getEmail(), Map.of("role", u.getRole().name(), "uid", u.getId()));
    String refresh = jwtUtil.generateRefreshToken(u.getEmail(), Map.of("uid", u.getId()));
    return new TokenResponse(access, refresh);
  }

  public UserEntity me(String email) { return users.findByEmail(email).orElseThrow(); }

  public UserEntity updateProfile(UserEntity me, UpdateProfileRequest req) {
    if (req.getDisplayName() != null) me.setDisplayName(req.getDisplayName());
    if (req.getAvatarUrl() != null) me.setAvatarUrl(req.getAvatarUrl());
    if (req.getBio() != null) me.setBio(req.getBio());
    if (req.getHomeCity() != null) me.setHomeCity(req.getHomeCity());
    me.setUpdatedAt(Instant.now());
    return users.save(me);
  }
}
