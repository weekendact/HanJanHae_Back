package com.back.hanjanhae.cocktail.service;

import com.back.hanjanhae.cocktail.dto.responseDTO.CocktailSetRandomResponseDTO;
import com.back.hanjanhae.cocktail.model.entity.Cocktail;
import com.back.hanjanhae.cocktail.model.repository.CocktailRandomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CocktailSetRandomService {
    private final CocktailRandomRepository cocktailRandomRepository;

    public CocktailSetRandomService(CocktailRandomRepository cocktailRandomRepository) {
        this.cocktailRandomRepository = cocktailRandomRepository;
    }

    public List<CocktailSetRandomResponseDTO> recommendRandomCocktails() {
        // 모든 칵테일을 조회
        List<Cocktail> listCocktails = cocktailRandomRepository.findAll();

        // 랜덤하게 7개의 칵테일 선택 (중복 제거) - 리스트를 무작위로 섞은 후 상위 7개 반환
        Collections.shuffle(listCocktails);
        List<Cocktail> randomCocktails = listCocktails.stream().limit(7).collect(Collectors.toList());

//        // 랜덤하게 7개의 칵테일 선택 -> 데이터가 반복되는 오류 발생
//        List<Cocktail> randomCocktails =
//                IntStream.range(0, 7)
//                        .mapToObj(i -> listCocktails.get(new Random().nextInt(listCocktails.size())))
//                        .collect(Collectors.toList());

        //DTO로 매핑
        List<CocktailSetRandomResponseDTO> cocktailSetRandomResponseDTOList = new ArrayList<>();

        for (Cocktail cocktail : randomCocktails) {
            CocktailSetRandomResponseDTO cocktailSetRandomResponseDTO = new CocktailSetRandomResponseDTO();
            cocktailSetRandomResponseDTO.setCocktailName(cocktail.getCocktailName());
            cocktailSetRandomResponseDTO.setCocktailPicture(cocktail.getCocktailPicture());
            cocktailSetRandomResponseDTOList.add(cocktailSetRandomResponseDTO);
        }

        return cocktailSetRandomResponseDTOList;
    }
}
