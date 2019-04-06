package com.retask.game.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retask.game.message.request.TaskRequest;
import com.retask.game.message.response.TaskResponse;
import com.retask.game.model.Task;
import com.retask.game.model.Upload;
import com.retask.game.model.User;
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
			
			uploads = uploadRepository.findBySourceTypeAndId("task", userTask.getTask_id());
			
			taskResponse = new TaskResponse(userTask.getTask());
			
			taskResponse.setUploads(uploads);
			
			tasksResponse.add(taskResponse);
		}
		
		return tasksResponse;
	}
	
	/**
	 * create tasks for a user, create the UserTask link and the uploads
	 * 
	 * @param rewardRequests
	 * @return
	 */
	public boolean createTasksForUsername(List<TaskRequest> taskRequests, User user) {

		Task taskToSave;
		UserTask userTaskToSave;
		Upload upload;

		for (TaskRequest taskRequest : taskRequests) {
			
			//Save The Task
			taskRequest.setUpdateDateTime();
			taskRequest.setCreateDateTime();

			taskToSave = new Task(taskRequest);
			
			System.out.println(taskToSave.getStartdate());
			// makes sure it is a new Task
			taskToSave.setId(null);
				
			taskRepository.save(taskToSave);
			
			// Save the UserTask 
			userTaskToSave = new UserTask();
			
			userTaskToSave.setId(null);
			userTaskToSave.setUsername(user.getUsername());
			userTaskToSave.setTask_id(taskToSave.getId());
			
			userTaskRepository.save(userTaskToSave);
			
					
			// save the uploads associated with a task
			for (int i=0; i<taskRequest.getUploads().size(); i++) {
				upload = new Upload();
				upload = taskRequest.getUploads().get(i);
				upload.setId(null);
				upload.setCreateDateTime();
				upload.setUpdateDateTime();
				upload.setUploadable_type("task");
				upload.setUploadable_id(taskToSave.getId());
				
				uploadRepository.save(upload);
			}
		}

		return true;
	}

	/**
	 * create tasks for a user, create the UserTask link and the uploads
	 * 
	 * @param rewardRequests
	 * @return
	 */
	public boolean updateTasksForUsername(List<TaskRequest> taskRequests, User user) {

		Task taskToSave;
		Upload upload;

		for (TaskRequest taskRequest : taskRequests) {
			
			//Save The Task
			taskRequest.setUpdateDateTime();
			taskRequest.setCreateDateTime();

			taskToSave = new Task(taskRequest);
			
			taskRepository.save(taskToSave);
			
					
			// save the uploads associated with a task
			for (int i=0; i<taskRequest.getUploads().size(); i++) {
				upload = new Upload();
				upload = taskRequest.getUploads().get(i);
				upload.setCreateDateTime();
				upload.setUpdateDateTime();
				upload.setUploadable_type("task");
				upload.setUploadable_id(taskToSave.getId());
				
				uploadRepository.save(upload);
			}
		}

		return true;
	}
	
	/**
	 * update task and uploads associated with a task for a user.
	 * 
	 * @param rewardRequests
	 * @return
	 */
	public boolean updateTasksForUsername(List<TaskRequest> taskRequests, String username) {

		Task taskToSave;
		Upload upload;

		for (TaskRequest taskRequest : taskRequests) {
			
			//Update The Task
			taskRequest.setUpdateDateTime();

			taskToSave = new Task(taskRequest);
			taskRepository.save(taskToSave);
			
					
			// save the uploads associated with a task
			for (int i=0; i<taskRequest.getUploads().size(); i++) {
				upload = new Upload();
				upload = taskRequest.getUploads().get(i);
				upload.setCreateDateTime();
				upload.setUpdateDateTime();
				upload.setUploadable_type("task");
				upload.setUploadable_id(taskToSave.getId());
				
				uploadRepository.save(upload);
			}
		}

		return true;
	}
	
}
