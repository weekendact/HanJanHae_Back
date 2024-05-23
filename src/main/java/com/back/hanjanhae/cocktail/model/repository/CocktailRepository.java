package com.back.hanjanhae.cocktail.model.repository;

import com.back.hanjanhae.cocktail.model.entity.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {
    @Query(value = "SELECT * FROM cocktail ORDER BY cocktail_likes DESC LIMIT 5", nativeQuery = true)
    List<Cocktail> findTop5ByOrderByCocktailLikesDesc();
}
