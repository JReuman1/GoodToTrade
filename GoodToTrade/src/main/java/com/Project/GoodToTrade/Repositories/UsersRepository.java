package com.Project.GoodToTrade.Repositories;

import com.Project.GoodToTrade.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}
