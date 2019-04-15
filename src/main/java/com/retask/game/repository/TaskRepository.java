package com.retask.game.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.retask.game.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	
	@Query("SELECT a FROM Task a where a.id = :id order by startdate asc") 
    Task findTaskById(@Param("id") Long id);
	
	@Query("SELECT a FROM Task a where a.username = :username and a.startdate <= :startdate and a.enddate >= :enddate order by startdate asc") 
    List<Task> findTaskByUserDate(@Param("username") String username, @Param("startdate") Date startdate, @Param("enddate") Date enddate);

	/**
	 * Use this query if you have a range of dates to get tasks over
	 * @param username
	 * @param startdate
	 * @param enddate
	 * @return
	 */
	@Query("SELECT a FROM Task a where a.username = :username and a.startdate >= :startdate and a.enddate <= :enddate order by startdate asc") 
    List<Task> findTaskByDateRange(@Param("username") String username, @Param("startdate") Date startdate, @Param("enddate") Date enddate);
}