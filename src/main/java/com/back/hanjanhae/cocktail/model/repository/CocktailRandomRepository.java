package com.back.hanjanhae.cocktail.model.repository;

import com.back.hanjanhae.cocktail.model.entity.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CocktailRandomRepository extends JpaRepository<Cocktail, Long> {
    List<Cocktail> findAll();
}
