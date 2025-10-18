package com.myeventsapp.event;

import com.myeventsapp.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
  boolean existsByEventIdAndUser(Long eventId, UserEntity user);
  long countByEventId(Long eventId);
}
