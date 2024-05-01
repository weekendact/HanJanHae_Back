package cocktailDetails.model.repository;

import cocktailDetails.model.entity.CocktailDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailDetailsRepository extends JpaRepository<Long, CocktailDetails> {
}
