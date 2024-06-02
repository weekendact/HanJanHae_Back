package com.back.hanjanhae.cocktailDetails.model.repository;

import com.back.hanjanhae.cocktailDetails.model.entity.CocktailDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailDetailsRepository extends JpaRepository<CocktailDetails,Long> {
}
