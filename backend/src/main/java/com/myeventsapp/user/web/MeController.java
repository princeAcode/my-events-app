package com.myeventsapp.user.web;

import com.myeventsapp.user.dto.UpdateProfileRequest;
import com.myeventsapp.user.dto.UserDto;
import com.myeventsapp.user.mapper.UserMapper;
import com.myeventsapp.user.service.UserService;
import com.myeventsapp.user.UserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MeController {
  private final UserService users;
  private final UserMapper mapper;

  @GetMapping("/me")
  public UserDto me(@AuthenticationPrincipal User principal) {
    UserEntity e = users.me(principal.getUsername());
    return mapper.toDto(e);
  }

  @PutMapping("/me")
  public UserDto update(@AuthenticationPrincipal User principal, @Valid @RequestBody UpdateProfileRequest req) {
    UserEntity e = users.me(principal.getUsername());
    return mapper.toDto(users.updateProfile(e, req));
  }
}
