package com.back.hanjanhae.community.dto.responsedto;

import lombok.Data;

@Data
public class CommunityInfoResponseDTO {
    private String communityName;
    private String communityPicture;
    private Long communityLikes;
}
