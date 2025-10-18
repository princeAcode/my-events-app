package com.myeventsapp.event;

import com.myeventsapp.user.UserEntity;
import jakarta.persistence.*;
import java.time.Instant;
import lombok.*;

@Entity @Table(name = "comments")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CommentEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(optional = false) private EventEntity event;
  @ManyToOne(optional = false) private UserEntity author;
  @Column(length = 4000) private String body;
  private Instant createdAt;
  private Instant updatedAt;
  private boolean deleted = false;
}
