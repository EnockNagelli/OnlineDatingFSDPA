package com.iiht.dating.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.iiht.dating.dto.ProfileDTO;
import com.iiht.dating.exception.ProfileNotFoundException;
import com.iiht.dating.model.Profile;
import com.iiht.dating.repository.ProfileRepository;
import com.iiht.dating.utils.DatingUtility;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepository repository;
	
	//-----------------------------------------------------------------------------------------------------
	public ProfileDTO saveProfile(ProfileDTO profileDTO) {
		Profile newProfile = DatingUtility.convertToProfile(profileDTO);
		repository.save(newProfile);
		return profileDTO;
	};
	//-----------------------------------------------------------------------------------------------------
	public ProfileDTO deleteProfile(Long userId) {
		Integer value = repository.deleteByProfileId(userId);
		if(value != null)
		return getProfileById(userId);
		else
			throw new ProfileNotFoundException("No Profile Found in the Database...");		
	};
	//-----------------------------------------------------------------------------------------------------
	public List<ProfileDTO> getAllProfiles() {
		List<Profile> allProfiles = repository.findAll();
		if(CollectionUtils.isEmpty(allProfiles))
			return null;
		else
			return allProfiles.stream().map(DatingUtility::convertToProfileDTO).collect(Collectors.toList());		
	};
	//-----------------------------------------------------------------------------------------------------
	public ProfileDTO getProfileById(Long userId) {
		Optional<Profile> profile = repository.findById(userId);
		return DatingUtility.convertOptionalToProfileDTO(profile);
	};	
}