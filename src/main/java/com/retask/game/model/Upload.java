package com.retask.game.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author gojoh
 *
 */
@Entity
@Table(name = "retask_uploads")
public class Upload implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String url;
	@NotBlank
	private String type;
	private Long uploadable_id;
	private String uploadable_type;

	private Timestamp dateTimeCreated;
	private Timestamp dateTimeUpdated;

	public Upload() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@JsonIgnore
	public Long getUploadable_id() {
		return uploadable_id;
	}

	@JsonIgnore
	public void setUploadable_id(Long uploadable_id) {
		this.uploadable_id = uploadable_id;
	}

	@JsonIgnore
	public String getUploadable_type() {
		return uploadable_type;
	}

	@JsonIgnore
	public void setUploadable_type(String uploadable_type) {
		this.uploadable_type = uploadable_type;
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

}