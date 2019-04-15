package com.retask.game.message.response;

import java.util.Date;
import java.util.List;

import com.retask.game.model.Task;
import com.retask.game.model.TaskStatus;
import com.retask.game.model.Upload;

public class TaskResponse extends Task {
	
	private Date dueDate;
	private String strStartDate;
	private String strEndDate;
	private Boolean completed;
	
	public TaskResponse() {}
	
	public TaskResponse(Task task) {
	     super(task);
	}
	
	private List<Upload> uploads;
	private List<TaskStatus> taskStatus;

	public List<Upload> getUploads() {
		return uploads;
	}

	public void setUploads(List<Upload> uploads) {
		this.uploads = uploads;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public List<TaskStatus> getTaskStatus() {
		return taskStatus;
	}

	public String getStrStartDate() {
		return strStartDate;
	}

	public void setTaskStatus(List<TaskStatus> taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	public void setStrStartDate(String strStartDate) {
		this.strStartDate = strStartDate;
	}

	public String getStrEndDate() {
		return strEndDate;
	}

	public void setStrEndDate(String strEndDate) {
		this.strEndDate = strEndDate;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	


}