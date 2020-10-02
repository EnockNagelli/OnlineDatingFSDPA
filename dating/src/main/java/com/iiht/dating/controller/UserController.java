package com.iiht.dating.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.dating.dto.InvalidUserExceptionResponse;
import com.iiht.dating.dto.UserDTO;
import com.iiht.dating.exception.InvalidUserException;
import com.iiht.dating.exception.UserNotFoundException;
import com.iiht.dating.services.UserService;

@RestController
@RequestMapping (value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	//-------------------------------------------------------------------------------------------------------------------------------
	// SERVICE OPERATIONS
	//-------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping (value = "/home")																					// 1. WORKING
 	public String landingPage() {
 		return "Welcome to Online Dating Application";
 	}

	//----------------------------------------------------------------------------------------------------------------
	// 			2. LOGIN AUTHENTICATION FOR DATING APPLICATION
	//----------------------------------------------------------------------------------------------------------------
	@PostMapping(value="/login")
	public ResponseEntity<Boolean> doLogin(@PathVariable("loginName") String loginName, @PathVariable("password") String password, BindingResult bindingResult) throws InvalidUserException {

		System.out.println("Inside Login authentication....");

		if(bindingResult.hasErrors())
			throw new InvalidUserException("Invalid User!! Please enter valid credentials...");
		else	
			return new ResponseEntity<Boolean>(userService.validateUser(loginName, password), HttpStatus.OK);
	}

	//----------------------------------------------------------------------------------------------------------------
	// 			3. ADD NEW USER INFORMATION FOR DATING APPLICATION
	//----------------------------------------------------------------------------------------------------------------
	@PostMapping(value = "/addUser")
	public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) throws InvalidUserException {

		System.out.println("Inside Save User Information....");
		
		if(bindingResult.hasErrors())
			throw new InvalidUserException("Invalid User Details!!!");
		else
			return new ResponseEntity<UserDTO>(userService.saveUser(userDTO), HttpStatus.OK);	
	}

	//----------------------------------------------------------------------------------------------------------------
	// 			4. LIST ALL REGISTERED USERS IN DATING APPLICATION
	//----------------------------------------------------------------------------------------------------------------
	@GetMapping(value = "/listAllUsers", produces = "application/json")
	public ResponseEntity<List<UserDTO>> findAllUsers() {
		return new ResponseEntity<List<UserDTO>>(userService.getAllUsers(), HttpStatus.OK);
	}

	//----------------------------------------------------------------------------------------------------------------
	// 			5. FETCH REGISTERED USERS AGAINST USERID IN DATING APPLICATION
	//----------------------------------------------------------------------------------------------------------------
	@GetMapping(value = "/getUserById/{userId}")															// 5. WORKING
	public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") Long userId, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors())
			throw new UserNotFoundException("Invalid UserId!! Please enter valid userId...");
		else	
			return new ResponseEntity<UserDTO>(userService.getUserById(userId), HttpStatus.OK);
	}	
	
	//----------------------------------------------------------------------------------------------------------------
	// 			6. DELETE REGISTERED USERS AGAINST USERID IN DATING APPLICATION
	//----------------------------------------------------------------------------------------------------------------
	@DeleteMapping(value = "/deleteUser/{userId}")																// 4. WORKING
	public ResponseEntity<UserDTO> deleteUser(@PathVariable("userId") Long userId, BindingResult bindingResult) throws InvalidUserException {
	
		if(bindingResult.hasErrors())
			throw new InvalidUserException("Invalid UserId!! Please enter valid userId...");
		else	
			return new ResponseEntity<UserDTO>(userService.deleteUser(userId), HttpStatus.OK);
	}

	//----------------------------------------------------------------------------------------------------------------
	// 			1. Exception Handling
	//----------------------------------------------------------------------------------------------------------------
	@ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<InvalidUserExceptionResponse> UserHandler(InvalidUserException exception) {
		InvalidUserExceptionResponse resp = new InvalidUserExceptionResponse(exception.getMessage(),System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value());
		ResponseEntity<InvalidUserExceptionResponse> response = new ResponseEntity<InvalidUserExceptionResponse>(resp, HttpStatus.BAD_REQUEST);		
		return response;
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<InvalidUserExceptionResponse> UserNotFoundHandler(UserNotFoundException exception) {
		InvalidUserExceptionResponse resp = new InvalidUserExceptionResponse(exception.getMessage(),System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		ResponseEntity<InvalidUserExceptionResponse> response = new ResponseEntity<InvalidUserExceptionResponse>(resp, HttpStatus.NOT_FOUND);
		return response;
	}	
}