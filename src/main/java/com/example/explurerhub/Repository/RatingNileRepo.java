package com.example.explurerhub.Repository;

import com.example.explurerhub.Model.RateNile;
import com.example.explurerhub.Model.RateOldCairo;
import com.example.explurerhub.Model.oldCairo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingNileRepo extends JpaRepository<RateNile,Long> {

    Optional<oldCairo> findByUserId(Long id);

    @Query("SELECT r.nile.id, r.nile.title, AVG(r.ratingValue) " +
            "FROM RateNile r " +
            "GROUP BY r.nile.id, r.nile.title")
    List<Object[]> findAverageRatingPerNile();

    @Query("SELECT r FROM RateNile r WHERE r.nile.id = :nileId AND r.user.id = :userId")
    Optional<RateNile> findByNileIdAndUserId(@Param("nileId") Long nileId,
                                                  @Param("userId") Long userId);

}
