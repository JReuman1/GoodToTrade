package com.Project.GoodToTrade.Repositories;

import com.Project.GoodToTrade.Models.TheLikes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheLikesRepository extends JpaRepository<TheLikes, Long> {
    List<TheLikes> findByLikedBy_Id(Long userId);
    List<TheLikes> findByLiked_Id(Long productId);
}
