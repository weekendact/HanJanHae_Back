package com.back.hanjanhae.drink.service;

import com.back.hanjanhae.cocktail.dto.CocktailListDTO;
import com.back.hanjanhae.drink.model.repository.DrinkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrinkListFindService {

    private final DrinkRepository drinkRepository;

    public DrinkListFindService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    public List<CocktailListDTO> findCocktail(String drinkTypeName) {
        return drinkRepository.findCocktailsByDrinkTypeName(drinkTypeName).stream() // 람다를 활용해 컬렉션을 함수형으로 처리
                .map(cocktail -> new CocktailListDTO(cocktail.getCocktailName(), cocktail.getCocktailPicture())) // 특정 값으로 변환해주는 작업, Cocktail 객체 -> CocktailListDTO 객체
                .collect(Collectors.toList()); // 결과를 리스트로 반환
    }
    // 람다식을 활용하여 cocktailName, cocktailPicture를 가져와서 결과를 리스트로 보냄
    // drinkTypeName으로 cocktail 리스트를 보내는 메서드
}
