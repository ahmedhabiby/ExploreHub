package com.example.explurerhub.Repository;

import com.example.explurerhub.Model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepo extends JpaRepository<Rating,Long> {

    Optional<Rating> findByUserId(Long id);

    @Query("SELECT r.cairoMosques.id, r.cairoMosques.title, AVG(r.ratingValue) " +
            "FROM Rating r " +
            "GROUP BY r.cairoMosques.id, r.cairoMosques.title")
    List<Object[]> findAverageRatingPerMosque();
}
