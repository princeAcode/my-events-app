package com.myeventsapp.user.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateProfileRequest {
  @Size(min = 2, max = 100)
  private String displayName;
  private String avatarUrl;
  @Size(max = 1000)
  private String bio;
  private String homeCity;
}
