package com.retask.game.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retask.game.message.response.RewardResponse;
import com.retask.game.message.response.TaskResponse;
import com.retask.game.model.Task;
import com.retask.game.model.Upload;
import com.retask.game.model.UserTask;
import com.retask.game.repository.TaskRepository;
import com.retask.game.repository.UploadRepository;
import com.retask.game.repository.UserTaskRepository;

@Service
public class TaskService {

	@Autowired
	private UserTaskRepository userTaskRepository;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private UploadRepository uploadRepository;
	
	/**
	 * gets the tasks by the username
	 * 
	 * @param username
	 * @return
	 */
	public List<TaskResponse> getTasksbyUsername(String username) {

		List<UserTask> userTasksList = userTaskRepository.findByUsername(username);
		List<Task> taskList = new ArrayList<Task>();
		List<Upload> uploads;
		List<TaskResponse> tasksResponse = new ArrayList<TaskResponse>();
		TaskResponse taskResponse;

		for (UserTask userTask: userTasksList) {	
			taskList.add(userTask.getTask());
			
			uploads = uploadRepository.findBySourceTypeAndId("task", userTask.getId());
			
			taskResponse = new TaskResponse(userTask.getTask());
			
			taskResponse.setUploads(uploads);
			
			tasksResponse.add(taskResponse);
		}
		
		return tasksResponse;
	}
	
}
