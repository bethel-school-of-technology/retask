package com.retask.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.retask.game.model.UserTask;

@Repository
public interface UserTaskRepository extends JpaRepository<UserTask, Long> {
	
    List<UserTask> findByUsername(String username);
    
    @Query("SELECT a FROM UserTask a where a.task_id = :task_id")
	List<UserTask> findByTaskId(@Param("task_id") Long id);
    
}