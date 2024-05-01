package likes.model.entity;

import cocktail.model.entity.Cocktail;
import community.model.entity.Community;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import users.model.entity.Users;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "likes")
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likes_id")
    private Long likesId;

    @ManyToOne
    @JoinColumn(name = "cocktail_id")
    private Cocktail cocktailId;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community communityId;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users usersId;
}
