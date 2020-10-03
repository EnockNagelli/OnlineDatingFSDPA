package com.iiht.dating.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.iiht.dating.dto.InvalidProfileExceptionResponse;
import com.iiht.dating.dto.ProfileDTO;
import com.iiht.dating.exception.InvalidProfileException;
import com.iiht.dating.exception.ProfileNotFoundException;
import com.iiht.dating.services.ProfileService;

@RestController
@RequestMapping("/profile")
public class UserProfileController {

	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private SessionFactory sessionFactory;

//	@PersistenceContext
//	private EntityManager entityManager;
	
//	Session session = entityManager.unwrap(Session.class); 
//	SessionFactory sessionFactory = session.getSessionFactory();
	
	//----------------------------------------------------------------------------------------------------------------
	// 			1. ADD NEW PROFILE INFORMATION FOR DATING APPLICATION
	//----------------------------------------------------------------------------------------------------------------
//	@PostMapping(value = "/addProfile")
//	public ResponseEntity<ProfileDTO> saveProfile(@Valid @RequestBody ProfileDTO profileDto, BindingResult bindingResult) throws InvalidProfileException {

	@PostMapping(value = "/addProfile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ProfileDTO> saveProfile(@Valid @RequestParam("userId") Long userId, @RequestParam("profileImage") MultipartFile profileImage, @RequestParam("contactNo") Long contactNo, @RequestParam("email") String email, @RequestParam("qualification") String qualification, @RequestParam("hobbies") String hobbies, @RequestParam("foodHabits") String foodHabits, @RequestParam("aboutMe") String aboutMe) throws Exception {

		System.out.println("Inside Save Profile Information....");

		ProfileDTO profileDTO = new ProfileDTO();

		profileDTO.setUserId(userId);
		
		MultipartFile multipartFile = profileImage;
		Blob blobImage = Hibernate.getLobCreator(sessionFactory.getCurrentSession()).createBlob(multipartFile.getInputStream(), multipartFile.getSize());
		profileDTO.setProfileImage(blobImage);

//		byte[] photoBytes = readBytesFromFile(photoFilePath);
//      person.setPhoto(photoBytes);		
		
		profileDTO.setContactNo(contactNo);
		profileDTO.setEmail(email);
		profileDTO.setQualification(qualification);
		profileDTO.setHobbies(hobbies);
		profileDTO.setFoodHabits(foodHabits);
		profileDTO.setAboutMe(aboutMe);

		System.out.println("User Id : "+userId);
		//System.out.println("User Photo : "+profileImage);
		System.out.println("Contact No : "+contactNo);
		System.out.println("Email : "+email);
		System.out.println("Qualification : "+qualification);
		System.out.println("Hobbies : "+hobbies);
		System.out.println("Food Habits : "+foodHabits);
		System.out.println("About Me : "+aboutMe);	
		
//		if(bindingResult.hasErrors())
//			throw new InvalidProfileException("Invalid Profile Details!!!");
//		else
			return new ResponseEntity<ProfileDTO>(profileService.saveProfile(profileDTO), HttpStatus.OK);	
	}
	
//	private static byte[] readBytesFromFile(String filePath) throws IOException {
//        File inputFile = new File(filePath);
//        FileInputStream inputStream = new FileInputStream(inputFile);
//         
//        byte[] fileBytes = new byte[(int) inputFile.length()];
//        inputStream.read(fileBytes);
//        inputStream.close();
//         
//        return fileBytes;
//    }	
	
	//----------------------------------------------------------------------------------------------------------------
	// 			2. LIST ALL USER PROFILES IN DATING APPLICATION
	//----------------------------------------------------------------------------------------------------------------
	@GetMapping(value = "/listAllProfiles", produces = "application/json")
	public ResponseEntity<List<ProfileDTO>> findAllProfiles() {
		return new ResponseEntity<List<ProfileDTO>>(profileService.getAllProfiles(), HttpStatus.OK);
	}

	//----------------------------------------------------------------------------------------------------------------
	// 			3. FETCH USER PROFILE AGAINST USERID IN DATING APPLICATION
	//----------------------------------------------------------------------------------------------------------------
	@GetMapping(value = "/getProfileById/{userId}")															// 5. WORKING
	public ResponseEntity<ProfileDTO> getProfileById(@PathVariable("userId") Long userId, BindingResult bindingResult) throws ProfileNotFoundException {
		
		if(bindingResult.hasErrors())
			throw new ProfileNotFoundException("Invalid UserId!! Please enter valid userId...");
		else	
			return new ResponseEntity<ProfileDTO>(profileService.getProfileById(userId), HttpStatus.OK);
	}	
	
	//----------------------------------------------------------------------------------------------------------------
	// 			4. DELETE REGISTERED USERS AGAINST USERID IN DATING APPLICATION
	//----------------------------------------------------------------------------------------------------------------
	@DeleteMapping(value = "/deleteProfile/{userId}")																// 4. WORKING
	public ResponseEntity<ProfileDTO> deleteProfile(@PathVariable("userId") Long userId, BindingResult bindingResult) throws InvalidProfileException {
	
		if(bindingResult.hasErrors())
			throw new InvalidProfileException("Invalid UserId!! Please enter valid userId...");
		else	
			return new ResponseEntity<ProfileDTO>(profileService.deleteProfile(userId), HttpStatus.OK);
	}

	//----------------------------------------------------------------------------------------------------------------
	// 			1. Exception Handling
	//----------------------------------------------------------------------------------------------------------------
	@ExceptionHandler(InvalidProfileException.class)
	public ResponseEntity<InvalidProfileExceptionResponse> ProfileHandler(InvalidProfileException exception) {
		InvalidProfileExceptionResponse resp = new InvalidProfileExceptionResponse(exception.getMessage(),System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value());
		ResponseEntity<InvalidProfileExceptionResponse> response = new ResponseEntity<InvalidProfileExceptionResponse>(resp, HttpStatus.BAD_REQUEST);
		return response;
	}
	
	@ExceptionHandler(ProfileNotFoundException.class)
	public ResponseEntity<InvalidProfileExceptionResponse> ProfileNotFoundHandler(ProfileNotFoundException exception) {
		InvalidProfileExceptionResponse resp = new InvalidProfileExceptionResponse(exception.getMessage(),System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		ResponseEntity<InvalidProfileExceptionResponse> response = new ResponseEntity<InvalidProfileExceptionResponse>(resp, HttpStatus.NOT_FOUND);
		return response;
	}
}