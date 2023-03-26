package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Likes;
import tn.esprit.spring.DAO.Repositories.LikesRepository;
import tn.esprit.spring.Services.Interfaces.ILikeService;
@Service
@AllArgsConstructor

public class LikeService implements ILikeService {
private LikesRepository likeRepository;
    @Override
    public Likes add(Likes likes) {
        return likeRepository.save(likes);
    }
}
