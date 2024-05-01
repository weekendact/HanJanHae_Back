package com.back.hanjanhae.material.service;

import com.back.hanjanhae.material.model.repository.MaterialRepository;
import org.springframework.stereotype.Service;

@Service
public class MaterialVERBService {

    private final MaterialRepository materialRepository;

    public MaterialVERBService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }
}
