package cocktail.service;

import cocktail.model.repository.CocktailRepository;
import org.springframework.stereotype.Service;

@Service
public class CocktailVERBService {

    private final CocktailRepository cocktailRepository;

    public CocktailVERBService(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }
}
