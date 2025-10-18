package com.myeventsapp.user.dto;

import com.myeventsapp.user.Role;
import lombok.Data;

@Data
public class UserDto {
  private Long id;
  private String email;
  private String displayName;
  private String avatarUrl;
  private String bio;
  private String homeCity;
  private Role role;
}
