package com.retask.game.model;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "retask_rewards")
public class Reward {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 3, max = 50)
	private String name;
	@NotBlank
	private String descr;

	@NotBlank
	private String username;

	private Long cost;

	@JsonIgnore
	private Timestamp dateTimeCreated;
	@JsonIgnore
	private Timestamp dateTimeUpdated;

	public Reward() {
	}

	public Reward(Reward reward) {
		// If you add a field to Task update this list

		this.id = reward.id;
		this.name = reward.name;
		this.descr = reward.descr;
		this.username = reward.username;
		this.cost = reward.cost;

		this.dateTimeCreated = reward.dateTimeCreated;
		this.dateTimeUpdated = reward.dateTimeUpdated;
	}

	public Reward(String name, String descr, Long cost) {
		this.name = name;
		this.descr = descr;
		this.cost = cost;
		this.setCreateDateTime();
		this.setUpdateDateTime();
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}