package com.example.explurerhub.Repository;

import com.example.explurerhub.Model.RateMusiums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingMusuimRepo extends JpaRepository<RateMusiums,Long> {

    Optional<RateMusiums> findByUserId(Long id);

    @Query("SELECT r.museum.id, r.museum.title, AVG(r.ratingValue) " +
            "FROM RateMusiums r " +
            "GROUP BY r.museum.id, r.museum.title")
    List<Object[]> findAverageRatingPerMuseum();

    @Query("SELECT r FROM RateMusiums r WHERE r.museum.id = :museumId AND r.user.id = :userId")
    Optional<RateMusiums> findByMuseumIdAndUserId(@Param("museumId") Long museumId,
                                                  @Param("userId") Long userId);

}
