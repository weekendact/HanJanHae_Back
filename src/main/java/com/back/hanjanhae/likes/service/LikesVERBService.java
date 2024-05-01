package com.back.hanjanhae.likes.service;

import com.back.hanjanhae.likes.model.repository.LikesRepository;
import org.springframework.stereotype.Service;

@Service
public class LikesVERBService {

    private final LikesRepository likesRepository;

    public LikesVERBService(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }
}
