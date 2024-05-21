package com.back.hanjanhae.cocktail.controller;

import com.back.hanjanhae.cocktail.model.entity.Cocktail;
import com.back.hanjanhae.cocktail.service.CocktailSetRandomService;
import com.back.hanjanhae.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cocktail")
public class CocktailController {

    private final CocktailSetRandomService cocktailSetRandomService;
    @Autowired
    public CocktailController(CocktailSetRandomService cocktailSetRandomService) {
        this.cocktailSetRandomService = cocktailSetRandomService;
    }

    @PostMapping("/random")
    public ResultDTO<?> recommendRandomCocktails() {
        return new ResultDTO<>().makeResult(HttpStatus.OK, "랜덤 추천", cocktailSetRandomService.recommendRandomCocktails(), "result");
    }
}
