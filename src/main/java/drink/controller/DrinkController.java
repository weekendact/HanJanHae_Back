package drink.controller;

import drink.service.DrinkVERBService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("drink")
public class DrinkController {

    private final DrinkVERBService drinkVERBService;

    public DrinkController(DrinkVERBService drinkVERBService) {
        this.drinkVERBService = drinkVERBService;
    }
}
