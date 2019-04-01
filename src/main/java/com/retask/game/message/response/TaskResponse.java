package com.retask.game.message.response;

import java.util.List;

import com.retask.game.model.Task;
import com.retask.game.model.Upload;

public class TaskResponse extends Task {
	
	public TaskResponse() {}
	
	public TaskResponse(Task task) {
	     super(task);
	}
	
	private List<Upload> uploads;

	public List<Upload> getUploads() {
		return uploads;
	}

	public void setUploads(List<Upload> uploads) {
		this.uploads = uploads;
	}
	


}