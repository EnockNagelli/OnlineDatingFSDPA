/*
 * Boundary Tests : To Test for validation of each field member of given model
 */
package com.iiht.dating.boundaryTestCases;

import static com.iiht.dating.utilTestClass.TestUtils.boundaryTestFile;
import static com.iiht.dating.utilTestClass.TestUtils.currentTest;
import static com.iiht.dating.utilTestClass.TestUtils.yakshaAssert;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iiht.dating.dto.DatingSpecsDTO;
import com.iiht.dating.dto.ProfileDTO;
import com.iiht.dating.dto.UserDTO;
import com.iiht.dating.utilTestClass.MasterData;
import com.iiht.dating.utils.DatingUtility;

@ExtendWith(SpringExtension.class)
@RunWith(JUnitPlatform.class)
public class BoundaryTests implements Serializable
{
	private static final long serialVersionUID = -6544854658457780865L;

	private Validator validator;

    //----------------------------------------------------------------------------------------------
    @BeforeEach
    public void setUp() {
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    //=============================================================================================
	//			1. User - Validating length of all properties
    //=============================================================================================
	@Test
	public void testFirstNameLength() throws Exception 
	{
		UserDTO userDTO = DatingUtility.convertToUserDTO(MasterData.getUserDetails());
		userDTO.setFirstName(null);
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
	//---------------------------------------------------------------------------------------------	
	@Test
	public void testLastNameLength() throws Exception 
	{
		UserDTO userDTO = DatingUtility.convertToUserDTO(MasterData.getUserDetails());
		userDTO.setLastName(null);
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
	//---------------------------------------------------------------------------------------------	
	@Test
	public void testDateOfBirth() throws Exception 
	{
		UserDTO userDTO = DatingUtility.convertToUserDTO(MasterData.getUserDetails());
		userDTO.setDateOfBirth(LocalDate.parse("2020-12-10"));
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
	//---------------------------------------------------------------------------------------------	
	@Test
	public void testGenderLength() throws Exception 
	{
		UserDTO userDTO = DatingUtility.convertToUserDTO(MasterData.getUserDetails());
		userDTO.setGender(null);
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(),!violations.isEmpty()? true : false, boundaryTestFile);
	}
	//---------------------------------------------------------------------------------------------	
	@Test
	public void testAddressLength() throws Exception
	{
		UserDTO userDTO = DatingUtility.convertToUserDTO(MasterData.getUserDetails());
		userDTO.setAddress(null);
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(),!violations.isEmpty()? true : false, boundaryTestFile);
	}
	//---------------------------------------------------------------------------------------------	
	@Test
	public void testLoginNameLength() throws Exception
	{
		UserDTO userDTO = DatingUtility.convertToUserDTO(MasterData.getUserDetails());
		userDTO.setLoginName(null);
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(),!violations.isEmpty()? true : false, boundaryTestFile);
	}	
	//---------------------------------------------------------------------------------------------	
	@Test
	public void testPasswordLength() throws Exception
	{
		UserDTO userDTO = DatingUtility.convertToUserDTO(MasterData.getUserDetails());
		userDTO.setPassword(null);
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(),!violations.isEmpty()? true : false, boundaryTestFile);
	}

    //=============================================================================================
	//			1.1 User - Post User Details Success or Failure
    //=============================================================================================
    @Test
    public void testPostUserSuccess() throws IOException 
    {
		UserDTO userDTO = DatingUtility.convertToUserDTO(MasterData.getUserDetails());
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
	    yakshaAssert(currentTest(), violations.isEmpty() ? true : false, boundaryTestFile);	    
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void testPostUserFailed() throws IOException {
		UserDTO userDTO = DatingUtility.convertToUserDTO(MasterData.getUserDetails());
		userDTO.setUserId(null);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
	    yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    //=============================================================================================
	//			2. Profile - Validating length of all properties
    //=============================================================================================
	@Test
	public void testEmailLength() throws Exception
	{
		ProfileDTO profileDTO = DatingUtility.convertToProfileDTO(MasterData.getProfileDetails());
		profileDTO.setEmail(null);
        Set<ConstraintViolation<ProfileDTO>> violations = validator.validate(profileDTO);
	    yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	//---------------------------------------------------------------------------------------------	
	@Test
	public void testQualification() throws Exception
	{
		ProfileDTO profileDTO = DatingUtility.convertToProfileDTO(MasterData.getProfileDetails());
		profileDTO.setQualification(null);
        Set<ConstraintViolation<ProfileDTO>> violations = validator.validate(profileDTO);
	    yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	//---------------------------------------------------------------------------------------------	
	@Test
	public void testHobbies() throws Exception
	{
		ProfileDTO profileDTO = DatingUtility.convertToProfileDTO(MasterData.getProfileDetails());
		profileDTO.setHobbies(null);
        Set<ConstraintViolation<ProfileDTO>> violations = validator.validate(profileDTO);
	    yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	//---------------------------------------------------------------------------------------------	
	@Test
	public void testFoodHabits() throws Exception
	{
		ProfileDTO profileDTO = DatingUtility.convertToProfileDTO(MasterData.getProfileDetails());
		profileDTO.setFoodHabits(null);
        Set<ConstraintViolation<ProfileDTO>> violations = validator.validate(profileDTO);
	    yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	//---------------------------------------------------------------------------------------------	
	@Test
	public void testAboutMe() throws Exception
	{
		ProfileDTO profileDTO = DatingUtility.convertToProfileDTO(MasterData.getProfileDetails());
		profileDTO.setAboutMe(null);
        Set<ConstraintViolation<ProfileDTO>> violations = validator.validate(profileDTO);
	    yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	
	//=============================================================================================
	//			2.1 Profile - Post Profile Success or Failure
    //=============================================================================================
    @Test
    public void testPostProfileSuccess() throws IOException {
		ProfileDTO profileDTO = DatingUtility.convertToProfileDTO(MasterData.getProfileDetails());
        Set<ConstraintViolation<ProfileDTO>> violations = validator.validate(profileDTO);
	    yakshaAssert(currentTest(), violations.isEmpty() ? true : false, boundaryTestFile);
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void testPostProfileFailed() throws IOException {
		ProfileDTO profileDTO = DatingUtility.convertToProfileDTO(MasterData.getProfileDetails());
		profileDTO.setUserId(null);
    	Set<ConstraintViolation<ProfileDTO>> violations = validator.validate(profileDTO);
	    yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    //=============================================================================================
	//			3. DatingSpecs - Validating length of all properties
    //=============================================================================================
	@Test
	public void testDatingDate() throws Exception
	{
		DatingSpecsDTO datingSpecsDTO = DatingUtility.convertToDatingSpecsDTO(MasterData.getDatingSpecsDetails());
		datingSpecsDTO.setDatingDate(null);
        Set<ConstraintViolation<DatingSpecsDTO>> violations = validator.validate(datingSpecsDTO);
	    yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
    //----------------------------------------------------------------------------------------------
	@Test
	public void testDatingTime() throws Exception
	{
		DatingSpecsDTO datingSpecsDTO = DatingUtility.convertToDatingSpecsDTO(MasterData.getDatingSpecsDetails());
		datingSpecsDTO.setDatingTime(null);
        Set<ConstraintViolation<DatingSpecsDTO>> violations = validator.validate(datingSpecsDTO);
	    yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
    //----------------------------------------------------------------------------------------------
	@Test
	public void testLocationLength() throws Exception
	{
		DatingSpecsDTO datingSpecsDTO = DatingUtility.convertToDatingSpecsDTO(MasterData.getDatingSpecsDetails());
		datingSpecsDTO.setLocation(null);
        Set<ConstraintViolation<DatingSpecsDTO>> violations = validator.validate(datingSpecsDTO);
	    yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
    //----------------------------------------------------------------------------------------------
	@Test
	public void testRequestStatusLength() throws Exception
	{
		DatingSpecsDTO datingSpecsDTO = DatingUtility.convertToDatingSpecsDTO(MasterData.getDatingSpecsDetails());
		datingSpecsDTO.setRequestStatus(null);
        Set<ConstraintViolation<DatingSpecsDTO>> violations = validator.validate(datingSpecsDTO);
	    yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
    //----------------------------------------------------------------------------------------------
	@Test
	public void testDatingRequestLength() throws Exception
	{
		DatingSpecsDTO datingSpecsDTO = DatingUtility.convertToDatingSpecsDTO(MasterData.getDatingSpecsDetails());
		datingSpecsDTO.setDatingRequest(null);
        Set<ConstraintViolation<DatingSpecsDTO>> violations = validator.validate(datingSpecsDTO);
	    yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	//=============================================================================================
	//			3.1 Profile - Post DatingSpecs Success or Failure
    //=============================================================================================
    @Test
    public void testPostDatingSpecsSuccess() throws IOException {
		DatingSpecsDTO datingSpecsDTO = DatingUtility.convertToDatingSpecsDTO(MasterData.getDatingSpecsDetails());
        Set<ConstraintViolation<DatingSpecsDTO>> violations = validator.validate(datingSpecsDTO);
	    yakshaAssert(currentTest(), violations.isEmpty() ? true : false, boundaryTestFile);
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void testPostDatingSpecsFailed() throws IOException {
		DatingSpecsDTO datingSpecsDTO = DatingUtility.convertToDatingSpecsDTO(MasterData.getDatingSpecsDetails());
		datingSpecsDTO.setUserId(null);
    	Set<ConstraintViolation<DatingSpecsDTO>> violations = validator.validate(datingSpecsDTO);
	    yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }	
}