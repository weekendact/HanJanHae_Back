package com.back.hanjanhae.community.service;

import com.back.hanjanhae.community.model.repository.CommunityRepository;
import org.springframework.stereotype.Service;

@Service
public class CommunityVERBService {

    private final CommunityRepository communityRepository;

    public CommunityVERBService(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }
}
