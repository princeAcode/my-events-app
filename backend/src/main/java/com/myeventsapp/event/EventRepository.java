package com.myeventsapp.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

  @Query("""
    SELECT e FROM EventEntity e
    WHERE e.deleted = false AND e.visibility = 'PUBLIC'
      AND (:q IS NULL OR lower(e.title) LIKE lower(concat('%', :q, '%')))
      AND (:city IS NULL OR e.city = :city)
      AND (:category IS NULL OR e.category = :category)
      AND (:fromTs IS NULL OR e.startAt >= :fromTs)
      AND (:toTs IS NULL OR e.startAt <= :toTs)
  """)
  Page<EventEntity> search(
      @Param("q") String q,
      @Param("city") String city,
      @Param("category") Category category,
      @Param("fromTs") java.time.Instant fromTs,
      @Param("toTs") java.time.Instant toTs,
      Pageable pageable);

  @Query("""
    SELECT e FROM EventEntity e
    WHERE e.deleted = false AND e.visibility = 'PUBLIC'
      AND e.latitude IS NOT NULL AND e.longitude IS NOT NULL
      AND (6371 * acos(cos(radians(:lat)) * cos(radians(e.latitude)) * cos(radians(e.longitude) - radians(:lng)) + sin(radians(:lat)) * sin(radians(e.latitude)))) <= :radiusKm
  """)
  Page<EventEntity> searchByRadius(@Param("lat") double lat, @Param("lng") double lng, @Param("radiusKm") double radiusKm, Pageable pageable);
}
