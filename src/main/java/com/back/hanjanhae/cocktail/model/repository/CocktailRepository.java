package com.back.hanjanhae.cocktail.model.repository;

import com.back.hanjanhae.cocktail.model.entity.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {
    @Query("SELECT c FROM Cocktail c ORDER BY c.cocktailLikes DESC")
    List<Cocktail> findTop5ByOrderByCocktailLikesDesc();
}
