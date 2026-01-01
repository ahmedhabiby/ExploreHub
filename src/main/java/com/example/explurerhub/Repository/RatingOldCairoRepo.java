package com.example.explurerhub.Repository;

import com.example.explurerhub.Model.RateMusiums;
import com.example.explurerhub.Model.RateOldCairo;
import com.example.explurerhub.Model.oldCairo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingOldCairoRepo extends JpaRepository<RateOldCairo,Long> {

    Optional<oldCairo> findByUserId(Long id);

    @Query("SELECT r.oldCairo.id, r.oldCairo.title, AVG(r.ratingValue) " +
            "FROM RateOldCairo r " +
            "GROUP BY r.oldCairo.id, r.oldCairo.title")
    List<Object[]> findAverageRatingPerOldCairo();

    @Query("SELECT r FROM RateOldCairo r WHERE r.oldCairo.id = :oldId AND r.user.id = :userId")
    Optional<RateOldCairo> findByOldIdAndUserId(@Param("oldId") Long oldId,
                                                  @Param("userId") Long userId);

}
