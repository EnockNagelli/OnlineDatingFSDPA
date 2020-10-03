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

import com.iiht.dating.dto.DatingSpecsDTO;
import com.iiht.dating.dto.InvalidDatingSpecsExceptionResponse;
import com.iiht.dating.exception.DatingSpecsNotFoundException;
import com.iiht.dating.exception.InvalidDatingSpecsException;
import com.iiht.dating.services.DatingService;

@RestController
@RequestMapping("/dating")
public class DatingController {

	@Autowired
	private DatingService datingService;

	//----------------------------------------------------------------------------------------------------------------
	// 			1. ADD NEW DATING SPECS INFORMATION FOR DATING APPLICATION
	//----------------------------------------------------------------------------------------------------------------
	@PostMapping(value = "/addDatingSpecs")
	public ResponseEntity<DatingSpecsDTO> saveDatingSpecs(@Valid @RequestBody DatingSpecsDTO datingSpecsDTO, BindingResult bindingResult) throws InvalidDatingSpecsException {

		System.out.println("Inside Save Profile Information....");
		
		if(bindingResult.hasErrors())
			throw new InvalidDatingSpecsException("Invalid Dating Specifications!!!");
		else
			return new ResponseEntity<DatingSpecsDTO>(datingService.saveDatingSpecs(datingSpecsDTO), HttpStatus.OK);	
	}
	
	//----------------------------------------------------------------------------------------------------------------
	// 			2. LIST ALL DATING SPECIFICATIONS OF DATING APPLICATION
	//----------------------------------------------------------------------------------------------------------------
	@GetMapping(value = "/listAllDatingSpecs", produces = "application/json")
	public ResponseEntity<List<DatingSpecsDTO>> findAllDatingSpecs() {
		return new ResponseEntity<List<DatingSpecsDTO>>(datingService.getAllDatingSpecs(), HttpStatus.OK);
	}

	//----------------------------------------------------------------------------------------------------------------
	// 			3. FETCH USER PROFILE AGAINST USERID IN DATING APPLICATION
	//----------------------------------------------------------------------------------------------------------------
	@GetMapping(value = "/getDatingSpecsById/{userId}")															// 5. WORKING
	public ResponseEntity<DatingSpecsDTO> getDatingSpecsById(@PathVariable("userId") Long userId, BindingResult bindingResult) throws DatingSpecsNotFoundException {
		
		if(bindingResult.hasErrors())
			throw new DatingSpecsNotFoundException("Invalid UserId!! Please enter valid userId...");
		else	
			return new ResponseEntity<DatingSpecsDTO>(datingService.getDatingSpecsById(userId), HttpStatus.OK);
	}	
	
	//----------------------------------------------------------------------------------------------------------------
	// 			4. DELETE REGISTERED USERS AGAINST USERID IN DATING APPLICATION
	//----------------------------------------------------------------------------------------------------------------
	@DeleteMapping(value = "/deleteDatingSpecs/{userId}")																// 4. WORKING
	public ResponseEntity<DatingSpecsDTO> deleteDatingSpecs(@PathVariable("userId") Long userId, BindingResult bindingResult) throws InvalidDatingSpecsException {
	
		if(bindingResult.hasErrors())
			throw new InvalidDatingSpecsException("Invalid UserId!! Please enter valid userId...");
		else	
			return new ResponseEntity<DatingSpecsDTO>(datingService.deleteDatingSpecs(userId), HttpStatus.OK);
	}

	//----------------------------------------------------------------------------------------------------------------
	// 			1. Exception Handling
	//----------------------------------------------------------------------------------------------------------------
	@ExceptionHandler(InvalidDatingSpecsException.class)
	public ResponseEntity<InvalidDatingSpecsExceptionResponse> ProfileHandler(InvalidDatingSpecsException exception) {
		InvalidDatingSpecsExceptionResponse resp = new InvalidDatingSpecsExceptionResponse(exception.getMessage(),System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value());
		ResponseEntity<InvalidDatingSpecsExceptionResponse> response = new ResponseEntity<InvalidDatingSpecsExceptionResponse>(resp, HttpStatus.BAD_REQUEST);
		return response;
	}
	
	@ExceptionHandler(DatingSpecsNotFoundException.class)
	public ResponseEntity<InvalidDatingSpecsExceptionResponse> ProfileNotFoundHandler(DatingSpecsNotFoundException exception) {
		InvalidDatingSpecsExceptionResponse resp = new InvalidDatingSpecsExceptionResponse(exception.getMessage(),System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		ResponseEntity<InvalidDatingSpecsExceptionResponse> response = new ResponseEntity<InvalidDatingSpecsExceptionResponse>(resp, HttpStatus.NOT_FOUND);
		return response;
	}
}