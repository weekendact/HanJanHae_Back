package com.back.hanjanhae.cocktail.controller;

import com.back.hanjanhae.cocktail.service.CocktailVERBService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cocktail")
public class CocktailController {

    private final CocktailVERBService cocktailVERBService;

    public CocktailController(CocktailVERBService cocktailVERBService) {
        this.cocktailVERBService = cocktailVERBService;
    }


}
