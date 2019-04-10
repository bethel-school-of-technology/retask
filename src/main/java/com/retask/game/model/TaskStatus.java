package com.retask.game.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "retask_taskstatus")
public class TaskStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long task_id;

	private Date completeDate;

	@JsonIgnore
	private Timestamp dateTimeCreated;
	@JsonIgnore
	private Timestamp dateTimeUpdated;

	public TaskStatus() {
	}

	public TaskStatus(TaskStatus taskStatus) {
		// If you add a field to Task update this list
		this.id = taskStatus.id;
		this.task_id = taskStatus.task_id;
		this.completeDate = taskStatus.completeDate;

		this.dateTimeCreated = taskStatus.dateTimeCreated;
		this.dateTimeUpdated = taskStatus.dateTimeUpdated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getDateTimeCreated() {
		return dateTimeCreated;
	}

	public void setDateTimeCreated(Timestamp dateTimeCreated) {
		this.dateTimeCreated = dateTimeCreated;
	}

	public Timestamp getDateTimeUpdated() {
		return dateTimeUpdated;
	}

	public void setDateTimeUpdated(Timestamp dateTimeUpdated) {
		this.dateTimeUpdated = dateTimeUpdated;
	}

	public void setCreateDateTime() {
		this.setDateTimeCreated(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	}

	public void setUpdateDateTime() {
		this.setDateTimeUpdated(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	}

	public Long getTask_id() {
		return task_id;
	}

	public void setTask_id(Long task_id) {
		this.task_id = task_id;
	}

	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

}