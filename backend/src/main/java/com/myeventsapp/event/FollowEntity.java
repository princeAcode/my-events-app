package com.myeventsapp.event;

import com.myeventsapp.user.UserEntity;
import jakarta.persistence.*;
import java.time.Instant;
import lombok.*;

@Entity @Table(name = "follows", uniqueConstraints = @UniqueConstraint(columnNames = {"follower_id","organizer_id"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class FollowEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(optional = false) private UserEntity follower;
  @ManyToOne(optional = false) private UserEntity organizer;
  private Instant createdAt;
}
