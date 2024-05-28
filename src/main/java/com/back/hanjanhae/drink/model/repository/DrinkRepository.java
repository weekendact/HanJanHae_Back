package com.back.hanjanhae.drink.model.repository;

import com.back.hanjanhae.cocktail.model.entity.Cocktail;
import com.back.hanjanhae.drink.model.entity.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {
    @Query("SELECT c " +
            "FROM Cocktail c " +
            "JOIN CocktailDetails cd ON cd.cocktailId.cocktailId = c.cocktailId " +
            "JOIN Drink d ON cd.drinkId.drinkId = d.drinkId " +
            "WHERE d.drinkType = :drinkType")
    List<Cocktail> findCocktailsByDrinkType(@Param("drinkType") int drinkType);
    // Cocktail, CocktailDetails, Drink 테이블 조인 후 drinkTypeName으로 cocktailName, cocktailPicture 불러옴
    // 불러온 값들을 List로 반환
}
