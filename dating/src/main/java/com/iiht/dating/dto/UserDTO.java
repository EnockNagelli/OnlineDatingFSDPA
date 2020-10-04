package com.iiht.dating.dto;

import java.time.LocalDate;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserDTO {

	private Long userId;

	@NotNull
	@Size(min=5, max=100)
	private String firstName;

	@NotNull
	@Size(min=5, max=100)
	private String lastName;

	@NotNull
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	@PastOrPresent
	private LocalDate dateOfBirth;

	@NotNull
	@Size(min=4, max=6)
	private String gender;

	@NotNull
	@Size(min=5, max=255)
	private String address;

	@NotNull
	@Size(min=5, max=100)
	private String loginName;

	@NotNull
	@Size(min=5, max=10, message="required")  
	private String password;

	//-------------------------------------------------------------------------------------
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	//-------------------------------------------------------------------------------------
	public UserDTO(Long userId, @NotNull @Size(min = 5, max = 50) String firstName,
			@NotNull @Size(min = 5, max = 50) String lastName, @NotNull @PastOrPresent LocalDate dateOfBirth,
			@NotNull @Size(min = 4, max = 6) String gender, @NotNull @Size(min = 5, max = 255) String address,
			@NotNull @Size(min = 5, max = 100) String loginName,
			@NotNull @Size(min = 5, max = 10, message = "required") String password) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.address = address;
		this.loginName = loginName;
		this.password = password;
	}
	//-------------------------------------------------------------------------------------
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	//-------------------------------------------------------------------------------------
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	//-------------------------------------------------------------------------------------
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	//-------------------------------------------------------------------------------------
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	//-------------------------------------------------------------------------------------
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	//-------------------------------------------------------------------------------------
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	//-------------------------------------------------------------------------------------
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	//-------------------------------------------------------------------------------------
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}