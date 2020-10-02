package com.iiht.dating.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.iiht.dating.dto.DatingSpecsDTO;
import com.iiht.dating.exception.ProfileNotFoundException;
import com.iiht.dating.model.DatingSpecs;
import com.iiht.dating.repository.DatingRepository;
import com.iiht.dating.utils.DatingUtility;

@Service
@Transactional
public class DatingServiceImpl implements DatingService {
	
	@Autowired
	private DatingRepository repository;
	
	//-----------------------------------------------------------------------------------------------------
	public DatingSpecsDTO saveDatingSpecs(DatingSpecsDTO datingSpecsDTO) {
		DatingSpecs newDatingSpecs = DatingUtility.convertToDatingSpecs(datingSpecsDTO);
		repository.save(newDatingSpecs);
		return datingSpecsDTO;
	};
	//-----------------------------------------------------------------------------------------------------
	public DatingSpecsDTO deleteDatingSpecs(Long userId) {
		Integer value = repository.deleteDatingSpecsById(userId);
		if(value != null)
		return getDatingSpecsById(userId);
		else
			throw new ProfileNotFoundException("No Profile Found in the Database...");		
	};
	//-----------------------------------------------------------------------------------------------------
	public List<DatingSpecsDTO> getAllDatingSpecs() {
		List<DatingSpecs> allDatingSpecs = repository.findAll();
		if(CollectionUtils.isEmpty(allDatingSpecs))
			return null;
		else
			return allDatingSpecs.stream().map(DatingUtility::convertToDatingSpecsDTO).collect(Collectors.toList());		
	};
	//-----------------------------------------------------------------------------------------------------
	public DatingSpecsDTO getDatingSpecsById(Long userId) {
		Optional<DatingSpecs> datingSpecs = repository.findById(userId);
		return DatingUtility.convertOptionalToDatingSpecsDTO(datingSpecs);		
	};
}