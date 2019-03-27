package com.retask.game.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retask.game.model.Task;
import com.retask.game.model.UserTask;
import com.retask.game.repository.TaskRepository;
import com.retask.game.repository.UserTaskRepository;

@Service
public class TaskService {

	@Autowired
	private UserTaskRepository userTaskRepository;
	@Autowired
	private TaskRepository taskRepository;
	
	/**
	 * gets the tasks by the username
	 * 
	 * @param username
	 * @return
	 */
	public List<Task> getTasksbyUsername(String username) {

		List<UserTask> userTasksList = userTaskRepository.findByUsername(username);
		List<Task> taskList = new ArrayList<Task>();

		for (UserTask userTask: userTasksList) {	
			taskList.add(userTask.getTask());
		}
		
		return taskList;
	}
	
}
