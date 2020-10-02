package com.iiht.dating.dto;

import java.sql.Blob;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProfileDTO {

	private Long userId;

	@NotNull
	@Lob
	private Blob profileImage;

	@NotNull
	@Size(min=5, max=10)
	private Long contactNo;
	
	@NotNull
	@Size(min=5, max=255)
	private String email;

	@NotNull
	@Size(min=5, max=255)
	private String qualification;

	@NotNull
	@Size(min=5, max=255)
	private String hobbies;

	@NotNull
	@Size(min=5, max=255)
	private String foodHabits;

	@NotNull
	@Size(min=5, max=255)
	private String aboutMe;

	//-------------------------------------------------------------------------------------
	public ProfileDTO() {
		super();
	}
	//-------------------------------------------------------------------------------------
	public ProfileDTO(Long userId, @NotNull Blob profileImage, @NotNull @Size(min = 5, max = 10) Long contactNo,
			@NotNull @Size(min = 5, max = 255) String email, @NotNull @Size(min = 5, max = 255) String qualification,
			@NotNull @Size(min = 5, max = 255) String hobbies, @NotNull @Size(min = 5, max = 255) String foodHabits,
			@NotNull @Size(min = 5, max = 255) String aboutMe) {
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
	//-------------------------------------------------------------------------------------
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	//-------------------------------------------------------------------------------------
	public Blob getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(Blob profileImage) {
		this.profileImage = profileImage;
	}
	//-------------------------------------------------------------------------------------
	public Long getContactNo() {
		return contactNo;
	}
	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}
	//-------------------------------------------------------------------------------------
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	//-------------------------------------------------------------------------------------
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	//-------------------------------------------------------------------------------------
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	//-------------------------------------------------------------------------------------
	public String getFoodHabits() {
		return foodHabits;
	}
	public void setFoodHabits(String foodHabits) {
		this.foodHabits = foodHabits;
	}
	//-------------------------------------------------------------------------------------
	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
}