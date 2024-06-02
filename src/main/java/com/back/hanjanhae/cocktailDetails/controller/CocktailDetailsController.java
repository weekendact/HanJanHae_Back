package com.back.hanjanhae.cocktailDetails.controller;

import com.back.hanjanhae.cocktailDetails.service.CocktailDetailsVERBService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cocktailDetails")
public class CocktailDetailsController {

    private final CocktailDetailsVERBService cocktailDetailsVERBService;

    public CocktailDetailsController(CocktailDetailsVERBService cocktailDetailsVERBService) {
        this.cocktailDetailsVERBService = cocktailDetailsVERBService;
    }
}
