package com.back.hanjanhae.cocktail.service;

import com.back.hanjanhae.cocktail.model.entity.Cocktail;
import com.back.hanjanhae.cocktail.model.repository.CocktailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CocktailSortByLikeService {

    private final CocktailRepository cocktailRepository;

    public CocktailSortByLikeService(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }
    public List<Cocktail> getTop5CocktailsByLikes() {
        List<Cocktail> cocktails = cocktailRepository.findTop5ByOrderByCocktailLikesDesc();
        return cocktails.size() > 5 ? cocktails.subList(0, 5) : cocktails;
    }
}
