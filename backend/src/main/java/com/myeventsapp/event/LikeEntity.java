package com.myeventsapp.event;

import com.myeventsapp.user.UserEntity;
import jakarta.persistence.*;
import java.time.Instant;
import lombok.*;

@Entity @Table(name = "likes", uniqueConstraints = @UniqueConstraint(columnNames = {"event_id","user_id"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class LikeEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(optional = false) private EventEntity event;
  @ManyToOne(optional = false) private UserEntity user;
  private Instant createdAt;
}
