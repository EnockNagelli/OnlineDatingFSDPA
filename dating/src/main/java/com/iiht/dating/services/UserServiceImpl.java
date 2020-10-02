package com.iiht.dating.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.iiht.dating.dto.UserDTO;
import com.iiht.dating.exception.UserNotFoundException;
import com.iiht.dating.model.User;
import com.iiht.dating.repository.UserRepository;
import com.iiht.dating.utils.DatingUtility;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repository; 

	//--------------------------------------------------------------------------------------------------------
	public UserDTO saveUser(UserDTO userDTO) {
		User newUser = DatingUtility.convertToUser(userDTO);
		repository.save(newUser);
		return userDTO;
	};
	//--------------------------------------------------------------------------------------------------------
	public UserDTO deleteUser(Long userId) {
		Integer value = repository.deleteByUserId(userId);
		if(value != null)
		return getUserById(userId);
		else
			throw new UserNotFoundException("No User Found in the Database...");	
	};
	//--------------------------------------------------------------------------------------------------------
	public List<UserDTO> getAllUsers() {
		List<User> allUsers = repository.findAll();
		if(CollectionUtils.isEmpty(allUsers))
			return null;
		else
			return allUsers.stream().map(DatingUtility::convertToUserDTO).collect(Collectors.toList());
	};
	//--------------------------------------------------------------------------------------------------------
	public UserDTO getUserById(Long userId) {
		Optional<User> user = repository.findById(userId);
		return DatingUtility.convertOptionalToUserDTO(user);
	};
	//--------------------------------------------------------------------------------------------------------
	public boolean validateUser(String loginName, String password) {
		User user = repository.validateUser(loginName, password);
		if (user == null)
			throw new UserNotFoundException("Could Not Find User with these credentials...");
		else
	        return true;
	};
}