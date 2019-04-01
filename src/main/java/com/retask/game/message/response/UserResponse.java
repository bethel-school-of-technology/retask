package com.retask.game.message.response;

import java.math.BigDecimal;

public class UserResponse {
    private String token;
    private String type = "Bearer";
    
    private String firstName;
   	private String lastName;
   	
   	private String phoneNbr;

    private String username;
    private String email;
    
    private BigDecimal points;

    public UserResponse(String accessToken, String type, String username, String firstName, String lastName,
			String email, String phoneNbr, BigDecimal points) {
		this.token = accessToken;
		this.type = type;
		this.setUsername(username);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPhoneNbr(phoneNbr);
		this.setPoints(points);
	}

    public String getPhoneNbr() {
		return phoneNbr;
	}

	public void setPhoneNbr(String phoneNbr) {
		this.phoneNbr = phoneNbr;
	}

	public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
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

	public BigDecimal getPoints() {
		return points;
	}

	public void setPoints(BigDecimal points) {
		this.points = points;
	}
    
    
}