package community.service;

import community.model.repository.CommunityRepository;
import org.springframework.stereotype.Service;

@Service
public class CommunityVERBService {

    private final CommunityRepository communityRepository;

    public CommunityVERBService(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }
}
