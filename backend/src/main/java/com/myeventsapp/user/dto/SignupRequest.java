package com.myeventsapp.user.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SignupRequest {
  @Email @NotBlank private String email;
  @Size(min = 6) private String password;
  @NotBlank private String displayName;
}
