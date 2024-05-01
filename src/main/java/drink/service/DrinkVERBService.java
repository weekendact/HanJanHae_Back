package drink.service;

import drink.model.repository.DrinkRepository;
import org.springframework.stereotype.Service;

@Service
public class DrinkVERBService {

    private final DrinkRepository drinkRepository;

    public DrinkVERBService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }
}
