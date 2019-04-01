package com.retask.game.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "retask_users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "username"
        }),
        @UniqueConstraint(columnNames = {
            "email"
        })
})
public class User implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
	private String firstName;
    @NotBlank
	private String lastName;

    @NotBlank
    @Size(min=3, max = 50)
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min=6, max = 100)
    private String password;
    
    private String phoneNbr;
    
    private BigDecimal points;
    
    @Lob
	private byte[] pic;
	private String picfileType;
	private String picfileName;
	private Timestamp dateTimeCreated;
	private Timestamp dateTimeUpdated; 

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "retask_user_roles", 
    	joinColumns = @JoinColumn(name = "user_id"), 
    	inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {}
    
    public User(User u) {
		// If you add a field to Task update this list
    	this.firstName = u.firstName;
        this.lastName = u.lastName;
        this.username = u.username;
        this.email = u.email;
        this.password = u.password;
        this.phoneNbr = u.phoneNbr;
        this.points = u.points;
        this.pic = u.pic;
        this.picfileType = u.picfileType;
    	this.picfileName = u.picfileName;
    	this.dateTimeCreated = u.dateTimeCreated;
    	this.dateTimeUpdated = u.dateTimeUpdated;
	}

    public User(String firstName, String lastName, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.setCreateDateTime();
        this.setUpdateDateTime();
    }

    public BigDecimal getPoints() {
		return points;
	}

	public void setPoints(BigDecimal points) {
		this.points = points;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	public String getPicfileType() {
		return picfileType;
	}

	public void setPicfileType(String picfileType) {
		this.picfileType = picfileType;
	}

	public String getPicfileName() {
		return picfileName;
	}

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

	public String getPhoneNbr() {
		return phoneNbr;
	}

	public void setPhoneNbr(String phoneNbr) {
		this.phoneNbr = phoneNbr;
	}
    
	
    
}