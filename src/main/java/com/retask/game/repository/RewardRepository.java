package com.retask.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.retask.game.model.Reward;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {
	
	@Query("SELECT a FROM Reward a where a.username = :username") 
	List<Reward> findByUsernameSorted(@Param("username") String username);
	
	List<Reward> findByUsername(String username);

}