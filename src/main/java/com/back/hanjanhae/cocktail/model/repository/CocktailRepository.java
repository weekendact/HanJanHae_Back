package com.back.hanjanhae.cocktail.model.repository;

import com.back.hanjanhae.cocktail.model.entity.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {
}
