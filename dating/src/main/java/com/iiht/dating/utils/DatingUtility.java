package com.iiht.dating.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.iiht.dating.dto.DatingSpecsDTO;
import com.iiht.dating.dto.ProfileDTO;
import com.iiht.dating.dto.UserDTO;
import com.iiht.dating.model.DatingSpecs;
import com.iiht.dating.model.Profile;
import com.iiht.dating.model.User;

public class DatingUtility {

	//=================================================================================================================================
	//				1. User Conversion : Model to DTO - AND - DTO to Model
	//=================================================================================================================================
	public static User convertToUser(UserDTO userDTO)	{
		
		User newUser = new User();

		newUser.setUserId(userDTO.getUserId());
		newUser.setFirstName(userDTO.getFirstName());
		newUser.setLastName(userDTO.getLastName());
		newUser.setDateOfBirth(userDTO.getDateOfBirth());
		newUser.setGender(userDTO.getGender());
		newUser.setAddress(userDTO.getAddress());
		newUser.setLoginName(userDTO.getLoginName());
		newUser.setPassword(userDTO.getPassword());
		
        return newUser;	
	};
	//---------------------------------------------------------------------------------------------------------------------------------
	public static UserDTO convertToUserDTO(User user)	{
		
		UserDTO userDTO = new UserDTO();

		userDTO.setUserId(user.getUserId());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setDateOfBirth(user.getDateOfBirth());
		userDTO.setGender(user.getGender());
		userDTO.setAddress(user.getAddress());
		userDTO.setLoginName(user.getLoginName());
		userDTO.setPassword(user.getPassword());

        return userDTO;
	};

	public static UserDTO convertOptionalToUserDTO(Optional<User> user)	{
		
		UserDTO userDTO = new UserDTO();
		
		User userData = user.get();

		userDTO.setUserId(userData.getUserId());
		userDTO.setFirstName(userData.getFirstName());
		userDTO.setLastName(userData.getLastName());
		userDTO.setDateOfBirth(userData.getDateOfBirth());
		userDTO.setGender(userData.getGender());
		userDTO.setAddress(userData.getAddress());
		userDTO.setLoginName(userData.getLoginName());
		userDTO.setPassword(userData.getPassword());

        return userDTO;
	};	
	
	//=================================================================================================================================
	//				2. Profile Details Conversion : Model to DTO - AND - DTO to Model
	//=================================================================================================================================
	public static Profile convertToProfile(ProfileDTO profileDTO)	{
		
		Profile newProfile = new Profile();

		newProfile.setUserId(profileDTO.getUserId());
		newProfile.setContactNo(profileDTO.getContactNo());
		newProfile.setEmail(profileDTO.getEmail());
		newProfile.setQualification(profileDTO.getQualification());
		newProfile.setProfileImage(profileDTO.getProfileImage());
		newProfile.setHobbies(profileDTO.getHobbies());
		newProfile.setFoodHabits(profileDTO.getFoodHabits());
		newProfile.setAboutMe(profileDTO.getAboutMe());
		
		return newProfile;
	};

	//---------------------------------------------------------------------------------------------------------------------------------
	public static ProfileDTO convertToProfileDTO(Profile profile) {
		
		ProfileDTO newProfile = new ProfileDTO();
		
		newProfile.setUserId(profile.getUserId());
		newProfile.setContactNo(profile.getContactNo());
		newProfile.setEmail(profile.getEmail());
		newProfile.setQualification(profile.getQualification());
		newProfile.setProfileImage(profile.getProfileImage());
		newProfile.setHobbies(profile.getHobbies());
		newProfile.setFoodHabits(profile.getFoodHabits());
		newProfile.setAboutMe(profile.getAboutMe());

		return newProfile;
	};
	
	public static ProfileDTO convertOptionalToProfileDTO(Optional<Profile> profile)	{
		
		ProfileDTO profileDTO = new ProfileDTO();
		
		Profile profileData = profile.get();

		profileDTO.setUserId(profileData.getUserId());
		profileDTO.setContactNo(profileData.getContactNo());
		profileDTO.setEmail(profileData.getEmail());
		profileDTO.setQualification(profileData.getQualification());
		profileDTO.setProfileImage(profileData.getProfileImage());
		profileDTO.setHobbies(profileData.getHobbies());
		profileDTO.setFoodHabits(profileData.getFoodHabits());
		profileDTO.setAboutMe(profileData.getAboutMe());
		
        return profileDTO;
	};	
	
	//=================================================================================================================================
	//				3. DatingSpecs Details Conversion : Model to DTO - AND - DTO to Model
	//=================================================================================================================================
	public static DatingSpecs convertToDatingSpecs(DatingSpecsDTO datingSpecsDTO)	{
		
		DatingSpecs newDatingSpecs = new DatingSpecs();

		newDatingSpecs.setUserId(datingSpecsDTO.getUserId());
		newDatingSpecs.setReceiverId(datingSpecsDTO.getReceiverId());
		newDatingSpecs.setDatingDate(datingSpecsDTO.getDatingDate());
		newDatingSpecs.setDatingTime(datingSpecsDTO.getDatingTime());
		newDatingSpecs.setLocation(datingSpecsDTO.getLocation());
		newDatingSpecs.setDatingRequest(datingSpecsDTO.getDatingRequest());
		newDatingSpecs.setRequestStatus(datingSpecsDTO.getDatingRequest());
		
		return newDatingSpecs;
	};

	//---------------------------------------------------------------------------------------------------------------------------------
	public static DatingSpecsDTO convertToDatingSpecsDTO(DatingSpecs datingSpecs) {
		
		DatingSpecsDTO newDatingSpecs = new DatingSpecsDTO();
		
		newDatingSpecs.setUserId(datingSpecs.getUserId());
		newDatingSpecs.setReceiverId(datingSpecs.getReceiverId());
		newDatingSpecs.setDatingDate(datingSpecs.getDatingDate());
		newDatingSpecs.setDatingTime(datingSpecs.getDatingTime());
		newDatingSpecs.setLocation(datingSpecs.getLocation());
		newDatingSpecs.setDatingRequest(datingSpecs.getDatingRequest());
		newDatingSpecs.setRequestStatus(datingSpecs.getDatingRequest());

		return newDatingSpecs;
	};

	public static DatingSpecsDTO convertOptionalToDatingSpecsDTO(Optional<DatingSpecs> datingSpecs) {
		
		DatingSpecsDTO newDatingSpecs = new DatingSpecsDTO();
		
		DatingSpecs dating = datingSpecs.get();
		
		newDatingSpecs.setUserId(dating.getUserId());
		newDatingSpecs.setReceiverId(dating.getReceiverId());
		newDatingSpecs.setDatingDate(dating.getDatingDate());
		newDatingSpecs.setDatingTime(dating.getDatingTime());
		newDatingSpecs.setLocation(dating.getLocation());
		newDatingSpecs.setDatingRequest(dating.getDatingRequest());
		newDatingSpecs.setRequestStatus(dating.getDatingRequest());

		return newDatingSpecs;
	};
	
	//---------------------------------------------------------------------------------------------------------------------------------
    public static List<UserDTO> convertToUserDtoList(List<User> userList) {
    	
        List<UserDTO> list = new ArrayList<UserDTO>();
        
        for(User userDto: userList) {
            list.add(convertToUserDTO(userDto));
        }       
        return list;
    }
	//---------------------------------------------------------------------------------------------------------------------------------
    public static List<ProfileDTO> convertToProfileDtoList(List<Profile> profileList) {
    	
		List<ProfileDTO> list = new ArrayList<ProfileDTO>();
		
		for(Profile profileDto : profileList) {
			list.add(convertToProfileDTO(profileDto));
		}
		return list;
    }
	//---------------------------------------------------------------------------------------------------------------------------------
    public static List<DatingSpecsDTO> convertToDatingSpecsDtoList(List<DatingSpecs> datingSpecsList) {
    	
		List<DatingSpecsDTO> list = new ArrayList<DatingSpecsDTO>();
		
		for(DatingSpecs datingDto : datingSpecsList) {
			list.add(convertToDatingSpecsDTO(datingDto));
		}
		return list;
    }    
}