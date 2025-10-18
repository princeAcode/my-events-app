package com.myeventsapp.event;

import com.myeventsapp.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RsvpRepository extends JpaRepository<RsvpEntity, Long> {
  long countByEventIdAndStatus(Long eventId, RsvpStatus status);
  boolean existsByUserAndEvent(UserEntity user, EventEntity event);
}
