package com.back.hanjanhae.drink.controller;

import com.back.hanjanhae.drink.dto.requestDTO.DrinkTypeRequestDTO;
import com.back.hanjanhae.drink.service.DrinkListFindService;
import com.back.hanjanhae.dto.ResultDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("drink")
public class DrinkController {

    private final DrinkListFindService drinkListFindService;

    public DrinkController(DrinkListFindService drinkListFindService) {
        this.drinkListFindService = drinkListFindService;
    }

    @PostMapping("/cocktails/search")
    public ResultDTO<?> cocktailList(@RequestBody DrinkTypeRequestDTO drinkTypeRequestDTO) {
        return new ResultDTO<>().makeResult(HttpStatus.OK, "리스트 조회", drinkListFindService.findCocktail(drinkTypeRequestDTO.getDrinkType()), "result");
    }
}
