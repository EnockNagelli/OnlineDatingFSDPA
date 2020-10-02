package com.iiht.dating.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DatingSpecs")
public class DatingSpecs implements Serializable {

	private static final long serialVersionUID = -1308383884940277975L;
	
	@Id
	@Column(name = "userId")
	private Long userId;
	private Long receiverId;	
	private LocalDate datingDate;
	private LocalTime datingTime;
	private String location;	
	private String requestStatus;								//Accepted / Rejected
	private String datingRequest;								//description about the dating	
	
	public DatingSpecs() {
		super();
	}

	public DatingSpecs(Long userId, Long receiverId, LocalDate datingDate, LocalTime datingTime, String location,
			String requestStatus, String datingRequest) {
		super();
		this.userId = userId;
		this.receiverId = receiverId;
		this.datingDate = datingDate;
		this.datingTime = datingTime;
		this.location = location;
		this.requestStatus = requestStatus;
		this.datingRequest = datingRequest;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public LocalDate getDatingDate() {
		return datingDate;
	}
	public void setDatingDate(LocalDate datingDate) {
		this.datingDate = datingDate;
	}

	public LocalTime getDatingTime() {
		return datingTime;
	}
	public void setDatingTime(LocalTime datingTime) {
		this.datingTime = datingTime;
	}

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getDatingRequest() {
		return datingRequest;
	}
	public void setDatingRequest(String datingRequest) {
		this.datingRequest = datingRequest;
	}
}