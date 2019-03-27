package com.retask.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.retask.game.model.UserTask;

@Repository
public interface UserTaskRepository extends JpaRepository<UserTask, Long> {
	
    List<UserTask> findByUsername(String username);
    
}