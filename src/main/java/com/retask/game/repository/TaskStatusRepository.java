package com.retask.game.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.retask.game.model.Task;
import com.retask.game.model.TaskStatus;

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {

	@Query("SELECT a FROM TaskStatus a where a.task_id = :task_id and a.completeDate between :startdate and :enddate")
	List<TaskStatus> findTaskStatusByTaskId(@Param("task_id") Long id, @Param("startdate") Date startdate, @Param("enddate") Date enddate);

}