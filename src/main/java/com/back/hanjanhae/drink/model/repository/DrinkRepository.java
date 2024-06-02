package com.back.hanjanhae.drink.model.repository;

import com.back.hanjanhae.drink.model.entity.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends JpaRepository<Drink,Long> {
}
