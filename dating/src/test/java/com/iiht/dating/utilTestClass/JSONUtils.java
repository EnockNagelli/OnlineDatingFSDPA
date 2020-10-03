package com.iiht.dating.utilTestClass;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiht.dating.dto.DatingSpecsDTO;
import com.iiht.dating.dto.ProfileDTO;
import com.iiht.dating.dto.UserDTO;

import java.io.IOException;
import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalTime;

public class JSONUtils 
{
    public static byte[] toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
    //---------------------------------------------------------------------------------------------------------------------------------
    public static UserDTO createUserDTO(Long userId, String firstName, String lastName, LocalDate dateOfBirth, String gender, String address, String loginName, String password) 
    {
    	UserDTO userDetails = new UserDTO();

    	userDetails.setUserId(userId);
    	userDetails.setFirstName(firstName);
    	userDetails.setLastName(lastName);
    	userDetails.setDateOfBirth(dateOfBirth);
    	userDetails.setGender(gender);
    	userDetails.setAddress(address);
    	userDetails.setLoginName(loginName);
    	userDetails.setPassword(password);

    	return userDetails;
    }
    //---------------------------------------------------------------------------------------------------------------------------------
    public static ProfileDTO createProfileDTO(Long userId, Blob profileImage, Long contactNo, String email, String qualification, String hobbies, String foodHabits, String aboutMe) 
    {
    	ProfileDTO profileDTO = new ProfileDTO();

    	profileDTO.setUserId(userId);
    	profileDTO.setProfileImage(profileImage);
    	profileDTO.setContactNo(contactNo);
    	profileDTO.setEmail(email);
    	profileDTO.setQualification(qualification);
    	profileDTO.setHobbies(hobbies);
    	profileDTO.setFoodHabits(foodHabits);
    	profileDTO.setAboutMe(aboutMe);
    	
    	return profileDTO;
    }
    //---------------------------------------------------------------------------------------------------------------------------------
    public static DatingSpecsDTO createDatingSpecsDTO(Long userId, Long receiverId, LocalDate datingDate, LocalTime datingTime, String location, String requestStatus, String datingRequest) 
    {
    	DatingSpecsDTO datingSpecsDTO = new DatingSpecsDTO();
    	
    	datingSpecsDTO.setUserId(userId);
    	datingSpecsDTO.setReceiverId(receiverId);
    	datingSpecsDTO.setDatingDate(datingDate);
    	datingSpecsDTO.setDatingTime(datingTime);
    	datingSpecsDTO.setLocation(location);
    	datingSpecsDTO.setRequestStatus(requestStatus);
    	datingSpecsDTO.getDatingRequest();
    	
    	return datingSpecsDTO;
    }
}