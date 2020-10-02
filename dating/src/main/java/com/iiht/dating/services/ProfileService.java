package com.iiht.dating.services;

import java.util.List;

import com.iiht.dating.dto.ProfileDTO;

public interface ProfileService {

	public ProfileDTO saveProfile(ProfileDTO profileDTO);
	public ProfileDTO deleteProfile(Long userId);
	public List<ProfileDTO> getAllProfiles();
	//--------------------------------------------------------------
	public ProfileDTO getProfileById(Long userId);
}
