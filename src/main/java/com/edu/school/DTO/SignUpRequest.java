package com.edu.school.DTO;

import lombok.Data;

@Data
public class SignUpRequest {

	private String name;
	private String email;
	private String password;
}
