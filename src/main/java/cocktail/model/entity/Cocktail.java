package cocktail.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cocktail")
public class Cocktail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cocktail_id")
    private Long cocktailId;

    @Column(name = "cocktail_name")
    private String cocktailName;

    @Column(name = "cocktail_how")
    private String cocktailHow;

    @Column(name = "cocktail_picture")
    private String cocktailPicture;

    @Column(name = "cocktail_likes")
    private Long cocktailLikes;
}
