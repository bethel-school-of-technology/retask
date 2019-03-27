package com.retask.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.retask.game.model.Reward;
import com.retask.game.model.Task;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {
//	
//	@Query("SELECT a FROM Task a where a.id = :id order by startdate asc") 
//    Task findTaskById(@Param("id") Long id);
	
	List<Reward> findByUsername(String username);

}