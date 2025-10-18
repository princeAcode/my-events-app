package com.myeventsapp.user;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "organizer_profiles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrganizerProfileEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToOne(optional = false)
  private UserEntity user;
  private String organizationName;
  private String websiteUrl;
  private String contactEmail;
}
