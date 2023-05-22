package com.Project.GoodToTrade.Services;

import com.Project.GoodToTrade.Models.Matches;
import com.Project.GoodToTrade.Models.Products;
import com.Project.GoodToTrade.Models.Users;
import com.Project.GoodToTrade.Repositories.MatchesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MatchesService {

    private final MatchesRepository matchesRepository;

    @Autowired
    public MatchesService(MatchesRepository matchesRepository) {
        this.matchesRepository = matchesRepository;
    }

    public List<Matches> getMatches() {
        return matchesRepository.findAll();
    }

    public Matches getMatch(Long id) {
        return matchesRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Match not found with id: " + id));
    }

    public List<Matches> getMatchesByUser1Id(Long user1Id) {
        return matchesRepository.findByUser1_Id(user1Id);
    }

    public List<Matches> getMatchesByUser2Id(Long user2Id) {
        return matchesRepository.findByUser2_Id(user2Id);
    }

    public List<Matches> getMatchesByProduct1Id(Long product1Id) {
        return matchesRepository.findByProduct1_Id(product1Id);
    }

    public List<Matches> getMatchesByProduct2Id(Long product2Id) {
        return matchesRepository.findByProduct2_Id(product2Id);
    }

    public Matches saveMatch(Matches match) {
        return matchesRepository.save(match);
    }

    public Matches createMatch(Users user1, Users user2, Products product1, Products product2) {
        Matches match = new Matches(user1, user2, product1, product2);
        return matchesRepository.save(match);
    }

    public void deleteMatch(Long id) {
        if (matchesRepository.existsById(id)) {
            matchesRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Match not found with id: " + id);
        }
    }
}
