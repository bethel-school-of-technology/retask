package com.retask.game.message.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserUpdateForm {
	@NotBlank
	@Size(min = 3, max = 50)
	private String firstName;

	@NotBlank
	private String lastName;
	
	private String phoneNbr;
	
	private BigDecimal points;

	public BigDecimal getPoints() {
		return points;
	}

	public void setPoints(BigDecimal points) {
		this.points = points;
	}

	public String getPhoneNbr() {
		return phoneNbr;
	}

	public void setPhoneNbr(String phoneNbr) {
		this.phoneNbr = phoneNbr;
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

}