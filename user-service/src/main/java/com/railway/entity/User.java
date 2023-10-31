package com.railway.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.railway.dto.Ticket;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Document(collection = "users")
public class User {
	
	@Transient
	public static final String SEQUENCE_NAME = "user_sequence";
	
	@Id
	private int userId;
	@NotNull
	@NotBlank
	private String userName;
	@Email(message = "Please Provide Correct Email")
	private String userEmail;
	@NotNull
	@NotBlank
	private String mobile;
	@NotNull
	private String password;
	@NotNull
	@NotEmpty
	private String gender;
	@Positive
	private int age;
	@NotNull
	private String address;
	private String role;
	
	
	public User(String userName, String userEmail,String mobile, String password, String gender, int age, String address,
			String role) {
		
		this.userName = userName;
		this.userEmail = userEmail;
		this.mobile=mobile;
		this.password = password;
		this.gender = gender;
		this.age = age;
		this.address = address;
		this.role = role;
	}

	

}
