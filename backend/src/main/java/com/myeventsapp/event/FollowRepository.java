package com.myeventsapp.event;

import com.myeventsapp.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<FollowEntity, Long> {
  boolean existsByFollowerAndOrganizer(UserEntity follower, UserEntity organizer);
  void deleteByFollowerAndOrganizer(UserEntity follower, UserEntity organizer);
}
