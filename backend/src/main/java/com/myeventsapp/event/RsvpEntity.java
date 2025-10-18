package com.myeventsapp.event;

import com.myeventsapp.user.UserEntity;
import jakarta.persistence.*;
import java.time.Instant;
import lombok.*;

@Entity @Table(name = "rsvps", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","event_id"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RsvpEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(optional = false) private UserEntity user;
  @ManyToOne(optional = false) private EventEntity event;
  @Enumerated(EnumType.STRING) private RsvpStatus status;
  private Instant createdAt;
}
