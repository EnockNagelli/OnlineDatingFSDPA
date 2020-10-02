package com.iiht.dating.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Profile")
public class Profile implements Serializable {

	private static final long serialVersionUID = -5925613614000678240L;
	
	@Id
	@Column(name = "userId")
	private Long userId;
	private Blob profileImage;
	private Long contactNo;	
	private String email;
	private String qualification;
	private String hobbies;
	private String foodHabits;
	private String aboutMe;
	
	public Profile() {
		super();
	}

	public Profile(Long userId, Blob profileImage, Long contactNo, String email, String qualification, String hobbies,
			String foodHabits, String aboutMe) {
		super();
		this.userId = userId;
		this.profileImage = profileImage;
		this.contactNo = contactNo;
		this.email = email;
		this.qualification = qualification;
		this.hobbies = hobbies;
		this.foodHabits = foodHabits;
		this.aboutMe = aboutMe;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Blob getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(Blob profileImage) {
		this.profileImage = profileImage;
	}

	public Long getContactNo() {
		return contactNo;
	}
	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getFoodHabits() {
		return foodHabits;
	}
	public void setFoodHabits(String foodHabits) {
		this.foodHabits = foodHabits;
	}

	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
}