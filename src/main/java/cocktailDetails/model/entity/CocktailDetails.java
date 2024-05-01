package cocktailDetails.model.entity;

import cocktail.model.entity.Cocktail;
import community.model.entity.Community;
import drink.model.entity.Drink;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import material.model.entity.Material;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cocktail_details")
public class CocktailDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cocktail_details_id")
    private Long cocktaillDetailsId;

    @ManyToOne
    @JoinColumn(name = "cocktail_id")
    private Cocktail cocktailId;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material materialId;

    @ManyToOne
    @JoinColumn(name = "drink_id")
    private Drink drinkId;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community communityId;

    @Column(name = "material_amount")
    private Float materialAmount;

    @Column(name = "material_count")
    private Integer materialCount;

    @Column(name = "material_weight")
    private Float materialWeight;

    @Column(name = "material_tea_spoon")
    private Integer materialTeaSpoon;

    @Column(name = "drink_amount")
    private Float drinkAmount;
}
