package com.iiht.dating.services;

import java.util.List;

import com.iiht.dating.dto.DatingSpecsDTO;

public interface DatingService {

	public DatingSpecsDTO saveDatingSpecs(DatingSpecsDTO datingSpecsDTO);
	public DatingSpecsDTO deleteDatingSpecs(Long userId);
	public List<DatingSpecsDTO> getAllDatingSpecs();
	//--------------------------------------------------------------
	public DatingSpecsDTO getDatingSpecsById(Long userId);
}