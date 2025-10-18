package com.myeventsapp.user;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.*;

@Entity @Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private String passwordHash;
  @Column(nullable = false)
  private String displayName;
  private String avatarUrl;
  @Column(length = 1000)
  private String bio;
  @Enumerated(EnumType.STRING)
  private Role role = Role.USER;
  private String homeCity;
  private Instant createdAt;
  private Instant updatedAt;
}
