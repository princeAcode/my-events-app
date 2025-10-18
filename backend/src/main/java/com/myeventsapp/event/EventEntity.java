package com.myeventsapp.event;

import com.myeventsapp.user.UserEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.*;

@Entity @Table(name = "events")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EventEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(optional = false)
  private UserEntity organizer;
  @Column(nullable = false)
  private String title;
  @Column(length = 8000)
  private String description;
  @Enumerated(EnumType.STRING)
  private Category category;
  private Instant startAt;
  private Instant endAt;
  private String venueName;
  private String address;
  private String city;
  private String country;
  private Double latitude;
  private Double longitude;
  private String coverImageUrl;
  private Integer capacity;
  private BigDecimal price;
  @Enumerated(EnumType.STRING)
  private Visibility visibility = Visibility.PUBLIC;
  @Enumerated(EnumType.STRING)
  private Status status = Status.SCHEDULED;
  private Instant createdAt;
  private Instant updatedAt;
  private boolean deleted = false;
}
