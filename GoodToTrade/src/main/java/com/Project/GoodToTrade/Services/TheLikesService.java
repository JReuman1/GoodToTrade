package com.Project.GoodToTrade.Services;

import com.Project.GoodToTrade.Models.Matches;
import com.Project.GoodToTrade.Models.TheLikes;
import com.Project.GoodToTrade.Models.Users;
import com.Project.GoodToTrade.Repositories.MatchesRepository;
import com.Project.GoodToTrade.Repositories.TheLikesRepository;
import com.Project.GoodToTrade.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TheLikesService {

    private final TheLikesRepository theLikesRepository;
    private final MatchesRepository matchesRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public TheLikesService(TheLikesRepository theLikesRepository, MatchesRepository matchesRepository, UsersRepository usersRepository) {
        this.theLikesRepository = theLikesRepository;
        this.matchesRepository = matchesRepository;
        this.usersRepository = usersRepository;
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
        Users likedUser = like.getLiked().getOwner();
        List<TheLikes> mutualLikes = theLikesRepository.findByLiked_IdAndLikedBy_Id(like.getLikedBy().getId(), likedUser.getId());

        if (!mutualLikes.isEmpty()) {
            // If a match would be created, save the 'like' and create the match
            TheLikes savedLike = theLikesRepository.save(like);
            createMatch(savedLike, mutualLikes);
            return savedLike;
        } else {
            // If no match would be created, simply save the 'like'
            return theLikesRepository.save(like);
        }
    }

    private void createMatch(TheLikes like, List<TheLikes> mutualLikes) {
        Matches match = new Matches();
        match.setUser1(like.getLikedBy());
        match.setUser2(like.getLiked().getOwner());
        match.setProduct1(like.getLiked());
        match.setProduct2(mutualLikes.get(0).getLiked());
        matchesRepository.save(match);
    }


    public void deleteLike(Long id) {
        if (theLikesRepository.existsById(id)) {
            theLikesRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Like not found with id: " + id);
        }
    }
}
