package com.iiht.dating.services;

import java.util.List;

import com.iiht.dating.dto.UserDTO;

public interface UserService {

	public UserDTO saveUser(UserDTO user);
	public UserDTO deleteUser(Long userId);
	public List<UserDTO> getAllUsers();
	//--------------------------------------------------------------
	public UserDTO getUserById(Long userId);
	public boolean validateUser(String loginName, String password);
}
