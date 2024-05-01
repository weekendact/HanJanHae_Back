package cocktailDetails.service;

import cocktailDetails.model.repository.CocktailDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class CocktailDetailsVERBService {

    private final CocktailDetailsRepository cocktailDetailsRepository;

    public CocktailDetailsVERBService(CocktailDetailsRepository cocktailDetailsRepository) {
        this.cocktailDetailsRepository = cocktailDetailsRepository;
    }
}