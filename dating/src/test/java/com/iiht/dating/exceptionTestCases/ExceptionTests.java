package com.iiht.dating.exceptionTestCases;

import static com.iiht.dating.utilTestClass.TestUtils.currentTest;
import static com.iiht.dating.utilTestClass.TestUtils.exceptionTestFile;
import static com.iiht.dating.utilTestClass.TestUtils.yakshaAssert;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.iiht.dating.dto.DatingSpecsDTO;
import com.iiht.dating.dto.ProfileDTO;
import com.iiht.dating.dto.UserDTO;
import com.iiht.dating.services.DatingService;
import com.iiht.dating.services.ProfileService;
import com.iiht.dating.services.UserService;
import com.iiht.dating.utilTestClass.MasterData;
import com.iiht.dating.utils.DatingUtility;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@WebMvcTest({UserService.class, ProfileService.class, DatingService.class})
@RunWith(JUnitPlatform.class)
public class ExceptionTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@MockBean
	private ProfileService profileService;

	@MockBean
	private DatingService datingService;

	//====================================================================================================================
	//			1. Exceptions tests regarding User Controller Operations
	//====================================================================================================================	
	@Test
	public void testUserForExceptionUponAddingNewUser() throws Exception
	{
		UserDTO userDTO = DatingUtility.convertToUserDTO(MasterData.getUserDetails());
		//userDTO.setUserId(null);
		
		//Mockito.when(userService.saveUser(userDTO)).thenReturn(userDTO);
		Mockito.when(userService.saveUser(null)).thenReturn(userDTO);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/addUser")
				.content(MasterData.asJsonString(userDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"), exceptionTestFile);
	}
	//--------------------------------------------------------------------------------------------------------------------
	@Test
	public void testUserLoginForExceptionUponLoggingWithNullValue() throws Exception
	{
		UserDTO userDTO = DatingUtility.convertToUserDTO(MasterData.getUserDetails());
		userDTO.setLoginName(null);

		Mockito.when(userService.saveUser(userDTO)).thenReturn(userDTO);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/addUser")
				.content(MasterData.asJsonString(userDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(), result.getResponse().getStatus() == 400 ? true : false, exceptionTestFile);		
	}
	//--------------------------------------------------------------------------------------------------------------------
	@Test
	public void testUserForExceptionUponFetchingUserByNullValue() throws Exception
	{
		//Mockito.when(userService.getUserById(null)).thenReturn(DatingUtility.convertToUserDTO(MasterData.getUserDetails()));
		Mockito.when(userService.getUserById(2L)).thenReturn(null);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/getUserById/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(), result.getResponse().getStatus() == 404 ? true : false, exceptionTestFile);		
	}
	//--------------------------------------------------------------------------------------------------------------------
	@Test
	public void testUserForExceptionUponDeletingUserByNullValue() throws Exception
	{
		Mockito.when(userService.deleteUser(2L)).thenReturn(null);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/user/deleteUser/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(), result.getResponse().getStatus() == 404 ? true : false, exceptionTestFile);		
	}	

	//====================================================================================================================
	//			2. Exceptions tests regarding Profile Controller Operations
	//====================================================================================================================	
	@Test
	public void testProfileForExceptionUponAddingNewProfile() throws Exception
	{
		ProfileDTO profileDTO = DatingUtility.convertToProfileDTO(MasterData.getProfileDetails());

		Mockito.when(profileService.saveProfile(null)).thenReturn(profileDTO);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/profile/addProfile")
				.content(MasterData.asJsonString(profileDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),	(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"), exceptionTestFile);
	}		
	//--------------------------------------------------------------------------------------------------------------------
	@Test
	public void testProfileForExceptionUponAddingProfileWithNullValue() throws Exception
	{
		ProfileDTO profileDTO = DatingUtility.convertToProfileDTO(MasterData.getProfileDetails());
		profileDTO.setUserId(null);

		Mockito.when(profileService.saveProfile(profileDTO)).thenReturn(profileDTO);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/stock/addStock")
				.content(MasterData.asJsonString(profileDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(), result.getResponse().getStatus() == 400 ? true : false, exceptionTestFile);		
	}
	//--------------------------------------------------------------------------------------------------------------------
	@Test
	public void testProfileForExceptionUponFetchingProfileByNullValue() throws Exception
	{
		Mockito.when(profileService.getProfileById(2L)).thenReturn(null);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/profile/getProfileById/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(), result.getResponse().getStatus() == 404 ? true : false, exceptionTestFile);		
	}
	//--------------------------------------------------------------------------------------------------------------------
	@Test
	public void testProfileForExceptionUponDeletingProfileByNullValue() throws Exception
	{
		ProfileDTO profileDTO = DatingUtility.convertToProfileDTO(MasterData.getProfileDetails());

		Mockito.when(profileService.deleteProfile(2L)).thenReturn(profileDTO);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/profile/deleteProfile/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(), result.getResponse().getStatus() == 404 ? true : false, exceptionTestFile);		
	}

	//====================================================================================================================
	//			3. Exceptions tests regarding Dating Controller Operations
	//====================================================================================================================	
	@Test
	public void testDatingControllerForExceptionUponAddingNewDatingSpecs() throws Exception
	{
		DatingSpecsDTO datingSpecsDTO = DatingUtility.convertToDatingSpecsDTO(MasterData.getDatingSpecsDetails());

		Mockito.when(datingService.saveDatingSpecs(null)).thenReturn(datingSpecsDTO);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/dating/addDatingSpecs")
				.content(MasterData.asJsonString(datingSpecsDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),	(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"), exceptionTestFile);
	}		
	//--------------------------------------------------------------------------------------------------------------------
	@Test
	public void testDatingControllerForExceptionUponAddingDatingSpecsWithNullValue() throws Exception
	{
		DatingSpecsDTO datingSpecsDTO = DatingUtility.convertToDatingSpecsDTO(MasterData.getDatingSpecsDetails());
		datingSpecsDTO.setUserId(null);

		Mockito.when(datingService.saveDatingSpecs(datingSpecsDTO)).thenReturn(datingSpecsDTO);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/dating/addDatingSpecs")
				.content(MasterData.asJsonString(datingSpecsDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(), result.getResponse().getStatus() == 400 ? true : false, exceptionTestFile);		
	}
	//--------------------------------------------------------------------------------------------------------------------
	@Test
	public void testDatingControllerForExceptionUponFetchingDatingSpecsByNullValue() throws Exception
	{
		Mockito.when(datingService.getDatingSpecsById(2L)).thenReturn(null);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dating/getDatingSpecsById/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(), result.getResponse().getStatus() == 404 ? true : false, exceptionTestFile);		
	}
	//--------------------------------------------------------------------------------------------------------------------
	@Test
	public void testDatingControllerForExceptionUponDeletingDatingSpecsByNullValue() throws Exception
	{
		DatingSpecsDTO datingSpecsDTO = DatingUtility.convertToDatingSpecsDTO(MasterData.getDatingSpecsDetails());

		Mockito.when(datingService.deleteDatingSpecs(2L)).thenReturn(datingSpecsDTO);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/dating/deleteDatingSpecs/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(), result.getResponse().getStatus() == 404 ? true : false, exceptionTestFile);		
	}	
}