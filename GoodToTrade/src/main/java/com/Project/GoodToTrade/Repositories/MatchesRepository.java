package com.Project.GoodToTrade.Repositories;

import com.Project.GoodToTrade.Models.Matches;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchesRepository extends JpaRepository<Matches, Long> {
    List<Matches> findByUser1_Id(Long user1Id);
    List<Matches> findByUser2_Id(Long user2Id);
    List<Matches> findByProduct1_Id(Long product1Id);
    List<Matches> findByProduct2_Id(Long product2Id);
}
