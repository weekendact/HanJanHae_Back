package likes.controller;

import likes.service.LikesVERBService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("likes")
public class LikesController {

    private final LikesVERBService likesVERBService;

    public LikesController(LikesVERBService likesVERBService) {
        this.likesVERBService = likesVERBService;
    }
}
