package community.controller;

import community.service.CommunityVERBService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("community")
public class CommunityController {

    private final CommunityVERBService communityVERBService;

    public CommunityController(CommunityVERBService communityVERBService) {
        this.communityVERBService = communityVERBService;
    }
}
