package com.bcp.test.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserRequest {
	@NotNull(message = "Email is mandatory")
	@NotEmpty(message = "Email is mandatory")
	@Email(message = "Email is not valid")
	private String email;
	@NotNull(message = "Password is mandatory")
	@NotEmpty(message = "Password is mandatory")
	private String password;


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
