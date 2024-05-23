package com.back.hanjanhae.cocktail.dto.responseDTO;

import lombok.Data;

@Data
public class CocktailInfoResponseDTO {
    private String cocktailName;
    private String cocktailPicture;
    private Long cocktailLikes;

}
