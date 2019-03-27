package com.retask.game.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "retask_rewards")
public class Reward {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
	private String name;
    @NotBlank
    @Size(min=3, max = 50)
	private String descr;
    
    @NotBlank
    private String username;

    private Long cost;

    @Lob
	private byte[] pic;
	private String picfileType;
	private String picfileName;
	 @JsonIgnore
	private Timestamp dateTimeCreated;
	 @JsonIgnore
	private Timestamp dateTimeUpdated; 

	public Reward() {}
	
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

    @JsonIgnore
	public byte[] getPic() {
		return pic;
	}
    @JsonIgnore
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
    @JsonIgnore
	public String getPicfileType() {
		return picfileType;
	}
    @JsonIgnore
	public void setPicfileType(String picfileType) {
		this.picfileType = picfileType;
	}
    @JsonIgnore
	public String getPicfileName() {
		return picfileName;
	}
    @JsonIgnore
	public void setPicfileName(String picfileName) {
		this.picfileName = picfileName;
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