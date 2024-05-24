package com.back.hanjanhae.community.controller;

import com.back.hanjanhae.community.service.CommunitySortByLikesService;
import com.back.hanjanhae.dto.ResultDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("community")
public class CommunityController {

    private final CommunitySortByLikesService communitySortByLikesService;

    public CommunityController(CommunitySortByLikesService communitySortByLikesService) {
        this.communitySortByLikesService = communitySortByLikesService;
    }

    @PostMapping("/top3")
    public ResultDTO<?> getTop5CocktailsByLikes(){ return new ResultDTO<>().makeResult(HttpStatus.OK, "인기순 커뮤니티", communitySortByLikesService.getTop3CommunitiesByLikes(), "result"); }

}
