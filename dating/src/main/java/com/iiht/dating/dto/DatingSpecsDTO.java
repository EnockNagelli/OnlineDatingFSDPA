package com.iiht.dating.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DatingSpecsDTO {

	private Long userId;

	@NotNull
	private Long receiverId;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	@PastOrPresent
	private LocalDate datingDate;

	@NotNull
	@PastOrPresent
	private LocalTime datingTime;

	@NotNull
	@Size(min=5, max=255)
	private String location;
	
	@NotNull
	@Size(min=5, max=255)
	private String requestStatus;								//Accepted / Rejected

	@NotNull
	@Size(min=5, max=255)
	private String datingRequest;								//description about the dating

	//-------------------------------------------------------------------------------------
	public DatingSpecsDTO() {
		super();
	}
	//-------------------------------------------------------------------------------------
	public DatingSpecsDTO(Long userId, @NotNull Long receiverId, @NotNull @PastOrPresent LocalDate datingDate,
			@NotNull @PastOrPresent LocalTime datingTime, @NotNull @Size(min = 5, max = 255) String location,
			@NotNull @Size(min = 5, max = 255) String requestStatus,
			@NotNull @Size(min = 5, max = 255) String datingRequest) {
		super();
		this.userId = userId;
		this.receiverId = receiverId;
		this.datingDate = datingDate;
		this.datingTime = datingTime;
		this.location = location;
		this.requestStatus = requestStatus;
		this.datingRequest = datingRequest;
	}
	//-------------------------------------------------------------------------------------
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	//-------------------------------------------------------------------------------------
	public Long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}
	//-------------------------------------------------------------------------------------
	public LocalDate getDatingDate() {
		return datingDate;
	}
	public void setDatingDate(LocalDate datingDate) {
		this.datingDate = datingDate;
	}
	//-------------------------------------------------------------------------------------
	public LocalTime getDatingTime() {
		return datingTime;
	}
	public void setDatingTime(LocalTime datingTime) {
		this.datingTime = datingTime;
	}
	//-------------------------------------------------------------------------------------
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	//-------------------------------------------------------------------------------------
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	//-------------------------------------------------------------------------------------
	public String getDatingRequest() {
		return datingRequest;
	}
	public void setDatingRequest(String datingRequest) {
		this.datingRequest = datingRequest;
	}	
}