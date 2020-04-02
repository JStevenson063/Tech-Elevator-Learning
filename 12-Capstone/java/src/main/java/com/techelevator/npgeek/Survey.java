package com.techelevator.npgeek;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class Survey {
	private String parkCode;

	@NotBlank(message = "Email cannot be blank")
	@Email(message = "Invalid email address")
	private String email;
	@NotBlank(message = "You must select a state")
	private String state;

	@NotNull(message = "A selection must be made")
	private String activityLevel;

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getActivityLevel() {
		return activityLevel;
	}

	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}

}
