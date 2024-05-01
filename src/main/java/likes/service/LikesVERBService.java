package likes.service;

import likes.model.repository.LikesRepository;
import org.springframework.stereotype.Service;

@Service
public class LikesVERBService {

    private final LikesRepository likesRepository;

    public LikesVERBService(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }
}
