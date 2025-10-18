package com.myeventsapp.user.web;

import com.myeventsapp.user.dto.*;
import com.myeventsapp.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final UserService service;

  @PostMapping("/signup")
  public ResponseEntity<Void> signup(@Valid @RequestBody SignupRequest req) {
    service.signup(req);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/login")
  public TokenResponse login(@Valid @RequestBody LoginRequest req) {
    return service.login(req);
  }

  @PostMapping("/refresh")
  public TokenResponse refresh(@RequestBody TokenResponse req) {
    return service.refresh(req.getRefreshToken());
  }
}
