package com.back.hanjanhae.cocktail.service;

import com.back.hanjanhae.cocktail.dto.responseDTO.CocktailInfoResponseDTO;
import com.back.hanjanhae.cocktail.model.entity.Cocktail;
import com.back.hanjanhae.cocktail.model.repository.CocktailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CocktailSortByLikeService {

    private final CocktailRepository cocktailRepository;

    public CocktailSortByLikeService(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }
    public List<CocktailInfoResponseDTO> getTop5CocktailsByLikes() {
        List<Cocktail> cocktailList = cocktailRepository.findTop5ByOrderByCocktailLikesDesc();
        List<CocktailInfoResponseDTO> cocktailInfoResponseDTOList = new ArrayList<>();

        for(Cocktail cocktail : cocktailList) {
            CocktailInfoResponseDTO cocktailInfoResponseDTO = new CocktailInfoResponseDTO();

            cocktailInfoResponseDTO.setCocktailName(cocktail.getCocktailName());
            cocktailInfoResponseDTO.setCocktailLikes(cocktail.getCocktailLikes());
            cocktailInfoResponseDTO.setCocktailPicture(cocktailInfoResponseDTO.getCocktailPicture());

            cocktailInfoResponseDTOList.add(cocktailInfoResponseDTO);
        }
        return cocktailInfoResponseDTOList;

    }
}
