package com.back.hanjanhae.community.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.back.hanjanhae.users.model.entity.Users;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "community")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id")
    private Long communityId;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users usersId;

    @Column(name = "community_name")
    private String communityName;

    @Column(name = "community_comment")
    private String communityComment;

    @Column(name = "community_how")
    private String communityHow;

    @Column(name = "community_picture")
    private String communityPicture;

    @Column(name = "community_likes")
    private Long communityLikes;
}
