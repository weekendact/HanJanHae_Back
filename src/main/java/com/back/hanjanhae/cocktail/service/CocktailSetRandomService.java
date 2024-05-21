package com.back.hanjanhae.cocktail.service;

import com.back.hanjanhae.cocktail.model.entity.Cocktail;
import com.back.hanjanhae.cocktail.model.repository.CocktailRandomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CocktailSetRandomService {
    @Autowired
    private CocktailRandomRepository cocktailRandomRepository;

    public List<Cocktail> recommendRandomCocktails() {
        // 모든 칵테일을 조회
        List<Cocktail> listCocktails = cocktailRandomRepository.findAll();

        // 랜덤하게 5개의 칵테일 선택
        List<Cocktail> randomCocktails =
                IntStream.range(0, 7)
                        .mapToObj(i -> listCocktails.get(new Random().nextInt(listCocktails.size())))
                        .collect(Collectors.toList());


        return randomCocktails;
    }

}
