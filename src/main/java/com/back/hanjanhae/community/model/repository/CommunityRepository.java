package com.back.hanjanhae.community.model.repository;

import com.back.hanjanhae.community.model.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    @Query(value = "SELECT * FROM community ORDER BY community_likes DESC LIMIT 3", nativeQuery = true)
    List<Community> findTop3ByOrderByCommunityLikesDesc();

}
