package com.back.hanjanhae.community.service;

import com.back.hanjanhae.community.dto.responsedto.CommunityInfoResponseDTO;
import com.back.hanjanhae.community.model.entity.Community;
import com.back.hanjanhae.community.model.repository.CommunityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommunitySortByLikesService {

    private final CommunityRepository communityRepository;

    public CommunitySortByLikesService(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    public List<CommunityInfoResponseDTO> getTop3CommunitiesByLikes() {
        List<Community> communityList = communityRepository.findTop3ByOrderByCommunityLikesDesc();

        return communityList.stream()
                .map(community -> {
                    CommunityInfoResponseDTO communityInfoResponseDTO = new CommunityInfoResponseDTO();
                    communityInfoResponseDTO.setCommunityName(community.getCommunityName());
                    communityInfoResponseDTO.setCommunityPicture(community.getCommunityPicture());
                    communityInfoResponseDTO.setCommunityLikes(community.getCommunityLikes());
                    return communityInfoResponseDTO;
                })
                .collect(Collectors.toList());
    }

}
