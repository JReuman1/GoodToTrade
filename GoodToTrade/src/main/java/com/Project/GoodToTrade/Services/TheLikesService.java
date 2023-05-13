package com.Project.GoodToTrade.Services;

import com.Project.GoodToTrade.Models.TheLikes;
import com.Project.GoodToTrade.Repositories.TheLikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TheLikesService {

    private final TheLikesRepository theLikesRepository;

    @Autowired
    public TheLikesService(TheLikesRepository theLikesRepository) {
        this.theLikesRepository = theLikesRepository;
    }

    public List<TheLikes> getLikes() {
        return theLikesRepository.findAll();
    }

    public TheLikes getLike(Long id) {
        return theLikesRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Like not found with id: " + id));
    }

    public List<TheLikes> getLikesByUserId(Long userId) {
        return theLikesRepository.findByLikedBy_Id(userId);
    }

    public List<TheLikes> getLikesByProductId(Long productId) {
        return theLikesRepository.findByLiked_Id(productId);
    }

    public TheLikes saveLike(TheLikes like) {
        return theLikesRepository.save(like);
    }

    public void deleteLike(Long id) {
        if (theLikesRepository.existsById(id)) {
            theLikesRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Like not found with id: " + id);
        }
    }
}
