package com.back.hanjanhae.material.model.repository;

import com.back.hanjanhae.material.model.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Long, Material> {
}
