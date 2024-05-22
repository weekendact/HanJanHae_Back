package com.back.hanjanhae.cocktail.controller;

import com.back.hanjanhae.cocktail.model.entity.Cocktail;
import com.back.hanjanhae.cocktail.service.CocktailSortByLikeService;
import com.back.hanjanhae.dto.ResultDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cocktail")
public class CocktailController {

    private final CocktailSortByLikeService cocktailSortByLikeService;

    public CocktailController(CocktailSortByLikeService cocktailSortByLikeService) {
        this.cocktailSortByLikeService = cocktailSortByLikeService;
    }

    @PostMapping("/top5")
    public ResultDTO<?> getTop5CocktailsByLikes(){
        return new ResultDTO<>().makeResult(HttpStatus.OK, "인기순 칵테일", cocktailSortByLikeService.getTop5CocktailsByLikes(), "result");
    }
}
