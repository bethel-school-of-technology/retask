package com.retask.game.model;

import java.io.Serializable;
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
@Table(name = "retask_tasks")
public class Task implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date startdate;
	private Date enddate;
	private Long level;
	private String name;
	private String description;
	private Long parent_task_id;
	private Long points;
	private String username;

	private Timestamp dateTimeCreated;
	private Timestamp dateTimeUpdated;

	public Task() {
	}

	public Task(Task task) {
		// If you add a field to Task update this list
		this.id = task.id;
		this.startdate = task.startdate;
		this.enddate = task.enddate;
		this.level = task.level;
		this.name = task.name;
		this.description = task.description;
		this.points = task.points;
		this.username = task.username;
		this.parent_task_id = task.parent_task_id;
		this.dateTimeCreated = task.dateTimeCreated;
		this.dateTimeUpdated = task.dateTimeUpdated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getParent_task_id() {
		return parent_task_id;
	}

	public void setParent_task_id(Long parent_task_id) {
		this.parent_task_id = parent_task_id;
	}

	@JsonIgnore
	public Timestamp getDateTimeCreated() {
		return dateTimeCreated;
	}

	@JsonIgnore
	public void setDateTimeCreated(Timestamp dateTimeCreated) {
		this.dateTimeCreated = dateTimeCreated;
	}

	@JsonIgnore
	public Timestamp getDateTimeUpdated() {
		return dateTimeUpdated;
	}

	@JsonIgnore
	public void setDateTimeUpdated(Timestamp dateTimeUpdated) {
		this.dateTimeUpdated = dateTimeUpdated;
	}

	@JsonIgnore
	public void setCreateDateTime() {
		this.setDateTimeCreated(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	}

	@JsonIgnore
	public void setUpdateDateTime() {
		this.setDateTimeUpdated(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	}

	public Long getPoints() {
		return points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

}