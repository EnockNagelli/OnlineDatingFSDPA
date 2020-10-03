package com.iiht.dating.functionalTestCases;

import static com.iiht.dating.utilTestClass.TestUtils.businessTestFile;
import static com.iiht.dating.utilTestClass.TestUtils.currentTest;
import static com.iiht.dating.utilTestClass.TestUtils.yakshaAssert;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.iiht.dating.controller.DatingController;
import com.iiht.dating.controller.UserController;
import com.iiht.dating.controller.UserProfileController;
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
@WebMvcTest({UserController.class, UserProfileController.class, DatingController.class})
@RunWith(SpringRunner.class)
public class TestController {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;

	@MockBean
	private ProfileService profileService;

	@MockBean
	private DatingService datingService;

	@InjectMocks
	private UserController userController;

	@InjectMocks
	private UserProfileController profileController;

	@InjectMocks
	private DatingController datingController;
	
	//---------------------------------------------------------------------------------------------------------------------------
	/*
	 * Description : This test is to perform setup for Mockito initiations
	 */
	@Before public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	//===========================================================================================================================
	//				I - Testing UserController Rest End Points
	//===========================================================================================================================
	//				1. Testing Rest End Point - /user/addUser
	//-- Test 1 : addUser -------------------------------------------------------------------------------------------------------
	/*
	 * Description : This test is to perform add new user in the Online Dating Application
	 */
	@Test 
	public void testAddUser() throws Exception 
	{ 
        UserDTO userDTO = DatingUtility.convertToUserDTO(MasterData.getUserDetails());
	
        Mockito.when(userService.saveUser(userDTO)).thenReturn(userDTO);
		
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/addUser")
				.content(MasterData.asJsonString(userDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
        System.out.println(result.getResponse().getContentAsString());
		System.out.println(userDTO);
		yakshaAssert(currentTest(),	result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(userDTO))? true : false, businessTestFile);
	}
	//-- BDD Test : addUesr -----------------------------------------------------------------------------------------------------
	@Test
	public void testAddUserBDD() throws Exception 
	{
		final int count[] = new int[1];
		
        UserDTO userDTO = DatingUtility.convertToUserDTO(MasterData.getUserDetails());
		
		Mockito.when(userService.saveUser(userDTO)).then(new Answer<UserDTO>() {
			@Override
			public UserDTO answer(InvocationOnMock invocation) throws Throwable {
				System.out.println("Called : testAddCompanyBDD");
				count[0]++;
				return userDTO;
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/addUser")
				.content(MasterData.asJsonString(userDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);	
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(count[0]);
		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
	}

	//---------------------------------------------------------------------------------------------------------------------------
	//				2. Testing Rest End Point - /user/deleteUser/{userId}
	//-- Test 2 : deleteUser ----------------------------------------------------------------------------------------------------
	@Test
	public void testDeleteUser() throws Exception
	{
        UserDTO userDTO = DatingUtility.convertToUserDTO(MasterData.getUserDetails());
		Long userId = userDTO.getUserId();
		
		Mockito.when(userService.deleteUser(userId)).thenReturn(userDTO);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/user/deleteUser/" + userId)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);				
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(),	result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(userDTO))? true : false, businessTestFile);
	}
	//-- BDD Test : deleteUser --------------------------------------------------------------------------------------------------
	@Test
	public void testDeleteUserBDD() throws Exception
	{
		final int count[] = new int[1];
	
        UserDTO userDTO = DatingUtility.convertToUserDTO(MasterData.getUserDetails());
		Long userId = userDTO.getUserId();
		
		Mockito.when(userService.deleteUser(userId)).then(new Answer<UserDTO>() {
			@Override
			public UserDTO answer(InvocationOnMock invocation) throws Throwable {
				System.out.println("Called : testDeleteUserBDD");
				count[0]++;
				return userDTO;
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/user/deleteUser/" + userId)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());
		System.out.println(count[0]);
		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
	}
	
	//---------------------------------------------------------------------------------------------------------------------------
	//				3. Testing Rest End Point - /user/getUserById/{userId}
	//-- Test 3 : getUserById ---------------------------------------------------------------------------------------------------
	@Test
	public void testFindUserById() throws Exception
	{
        UserDTO userDTO = DatingUtility.convertToUserDTO(MasterData.getUserDetails());
		Long userId = userDTO.getUserId();
		
		Mockito.when(userService.getUserById(userId)).thenReturn(userDTO);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/getUserById/" + userId)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(),	result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(userDTO))? true : false, businessTestFile);
	}
	//-- BDD Test : getUserById -------------------------------------------------------------------------------------------------
	@Test
	public void testFindUserByIdBDD() throws Exception
	{
		final int count[] = new int[1];
		
        UserDTO userDTO = DatingUtility.convertToUserDTO(MasterData.getUserDetails());
		Long userId = userDTO.getUserId();
		
		Mockito.when(userService.getUserById(userId)).then(new Answer<UserDTO>() {
			@Override
			public UserDTO answer(InvocationOnMock invocation) throws Throwable {
				System.out.println("Called : testFindUserByIdBDD");
				count[0]++;
				return userDTO;
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/getUserById/" + userId)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(count[0]);
		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);			
	}
	
	//---------------------------------------------------------------------------------------------------------------------------
	//				4. Testing Rest End Point - /user/listAllUsers
	//-- Test 4 : listAllUsers --------------------------------------------------------------------------------------------------
	/*
	 * Description : This test is to perform view all users from database
	 */
	@Test 
	public void testFindAllUsers() throws Exception 
	{ 
        UserDTO userDTO = DatingUtility.convertToUserDTO(MasterData.getUserDetails());
		
		List<UserDTO> list = new ArrayList<UserDTO>();
		list.add(userDTO);
		
		Mockito.when(userService.getAllUsers()).thenReturn(list);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/listAllUsers")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(MasterData.asJsonString(list));
		yakshaAssert(currentTest(), (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(list))? "true" : "false"),	businessTestFile);
	}
	//-- BDD Test : listAllUsersBDD ---------------------------------------------------------------------------------------------
	@Test
	public void testFindAllUsersBDD() throws Exception 
	{
		final int count[] = new int[1];

        UserDTO userDTO = DatingUtility.convertToUserDTO(MasterData.getUserDetails());
		
		List<UserDTO> list = new ArrayList<UserDTO>();
		list.add(userDTO);

		Mockito.when(userService.getAllUsers()).then(new Answer<List<UserDTO>>() {
			@Override
			public List<UserDTO> answer(InvocationOnMock invocation) throws Throwable {
				System.out.println("Called : testFindAllUsersBDD");
				count[0]++;
				return list;
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/listAllUsers")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(count[0]);
		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
	}
	
	//===========================================================================================================================
	//				II - Testing ProfileController Rest End Points
	//===========================================================================================================================
	//				1. Testing Rest End Point - /profile/addProfile
	//-- Test 1 : addProfile ----------------------------------------------------------------------------------------------------
	/*
	 * Description : This test is to perform add new Profile Details in the Online Dating Application
	 */
	@Test 
	public void testAddProfile() throws Exception 
	{ 
        ProfileDTO profileDTO = DatingUtility.convertToProfileDTO(MasterData.getProfileDetails());
	
        Mockito.when(profileService.saveProfile(profileDTO)).thenReturn(profileDTO);
		
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/profile/addProfile")
				.content(MasterData.asJsonString(profileDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
        System.out.println(result.getResponse().getContentAsString());
		System.out.println(profileDTO);
		yakshaAssert(currentTest(),	result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(profileDTO))? true : false, businessTestFile);
	}
	//-- BDD Test : addProfileBDD -----------------------------------------------------------------------------------------------
	@Test
	public void testAddProfileBDD() throws Exception
	{
		final int count[] = new int[1];
		
        ProfileDTO profileDTO = DatingUtility.convertToProfileDTO(MasterData.getProfileDetails());
		
		Mockito.when(profileService.saveProfile(profileDTO)).then(new Answer<ProfileDTO>() {
			@Override
			public ProfileDTO answer(InvocationOnMock invocation) throws Throwable {
				System.out.println("Called : testAddProfileBDD");
				count[0]++;
				return profileDTO;
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/profile/addProfile")
				.content(MasterData.asJsonString(profileDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);	
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(count[0]);
		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
	}	
	
	//---------------------------------------------------------------------------------------------------------------------------
	//				2. Testing Rest End Point - /profile/deleteProfile/{userId}
	//-- Test 2 : deleteProfile -------------------------------------------------------------------------------------------------
	@Test
	public void testDeleteProfile() throws Exception
	{
        ProfileDTO profileDTO = DatingUtility.convertToProfileDTO(MasterData.getProfileDetails());
        Long userId = profileDTO.getUserId();
       
		Mockito.when(profileService.deleteProfile(userId)).thenReturn(profileDTO);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/profile/deleteProfile/" + userId)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);				
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(),	result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(profileDTO))? true : false, businessTestFile);
	}
	//-- BDD Test : deleteProfileBDD --------------------------------------------------------------------------------------------
	@Test
	public void testDeleteProfileBDD() throws Exception 
	{
		final int count[] = new int[1];
	
        ProfileDTO profileDTO = DatingUtility.convertToProfileDTO(MasterData.getProfileDetails());
        Long userId = profileDTO.getUserId();
	
		Mockito.when(profileService.deleteProfile(userId)).then(new Answer<ProfileDTO>() {
			@Override
			public ProfileDTO answer(InvocationOnMock invocation) throws Throwable {
				System.out.println("Called : testDeleteProfileBDD");
				count[0]++;
				return profileDTO;
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/profile/deleteProfile/" + userId)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());
		System.out.println(count[0]);
		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
	}
	
	//---------------------------------------------------------------------------------------------------------------------------
	//				3. Testing Rest End Point - /profile/getProfileById/{userId}
	//-- Test 3 : getProfileById ------------------------------------------------------------------------------------------------
	@Test
	public void testFindProfileByUserId() throws Exception
	{
        ProfileDTO profileDTO = DatingUtility.convertToProfileDTO(MasterData.getProfileDetails());
        Long userId = profileDTO.getUserId();
        
		Mockito.when(profileService.getProfileById(userId)).thenReturn(profileDTO);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/profile/getProfileById/" + userId)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),	result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(profileDTO))? true : false, businessTestFile);		
	}
	//-- BDD Test : getProfileByIdBDD -------------------------------------------------------------------------------------------
	@Test
	public void testFindProfileByUserIdBDD() throws Exception 
	{
		final int count[] = new int[1];
		
        ProfileDTO profileDTO = DatingUtility.convertToProfileDTO(MasterData.getProfileDetails());
        Long userId = profileDTO.getUserId();

		Mockito.when(profileService.getProfileById(userId)).then(new Answer<ProfileDTO>() {
			@Override
			public ProfileDTO answer(InvocationOnMock invocation) throws Throwable {
				System.out.println("Called : testFindProfileByUserIdBDD");
				count[0]++;
				return profileDTO;
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/profile/getProfileById/" + userId)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(count[0]);
		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);			
	}	

	//---------------------------------------------------------------------------------------------------------------------------
	//				4. Testing Rest End Point - /user/listAllProfiles
	//-- Test 4 : listAllProfiles -----------------------------------------------------------------------------------------------
	/*
	 * Description : This test is to perform view all Profiles from database
	 */
	@Test 
	public void testFindAllProfiles() throws Exception 
	{ 
        ProfileDTO profileDTO = DatingUtility.convertToProfileDTO(MasterData.getProfileDetails());
		
		List<ProfileDTO> list = new ArrayList<ProfileDTO>();
		list.add(profileDTO);
		
		Mockito.when(profileService.getAllProfiles()).thenReturn(list);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/profile/listAllProfiles")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(MasterData.asJsonString(list));
		yakshaAssert(currentTest(), (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(list))? "true" : "false"),	businessTestFile);
	}
	//-- BDD Test : listAllProfilesBDD ------------------------------------------------------------------------------------------
	@Test
	public void testFindAllProfilesBDD() throws Exception 
	{
		final int count[] = new int[1];

        ProfileDTO profileDTO = DatingUtility.convertToProfileDTO(MasterData.getProfileDetails());
		
		List<ProfileDTO> list = new ArrayList<ProfileDTO>();
		list.add(profileDTO);

		Mockito.when(profileService.getAllProfiles()).then(new Answer<List<ProfileDTO>>() {
			@Override
			public List<ProfileDTO> answer(InvocationOnMock invocation) throws Throwable {
				System.out.println("Called : testFindAllProfilesBDD");
				count[0]++;
				return list;
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/profile/listAllProfiles")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(count[0]);
		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
	}
	
	//===========================================================================================================================
	//				III - Testing DatingController Rest End Points
	//===========================================================================================================================
	//				1. Testing Rest End Point - /dating/addDatingSpecs
	//-- Test 1 : addDatingSpecs ------------------------------------------------------------------------------------------------
	/*
	 * Description : This test is to perform add new DatingSpecs in the Online Dating Application
	 */
	@Test 
	public void testAddDatingSpecs() throws Exception 
	{ 
        DatingSpecsDTO datingSpecsDTO = DatingUtility.convertToDatingSpecsDTO(MasterData.getDatingSpecsDetails());
	
        Mockito.when(datingService.saveDatingSpecs(datingSpecsDTO)).thenReturn(datingSpecsDTO);
		
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/dating/addDatingSpecs")
				.content(MasterData.asJsonString(datingSpecsDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
        System.out.println(result.getResponse().getContentAsString());
		System.out.println(datingSpecsDTO);
		yakshaAssert(currentTest(),	result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(datingSpecsDTO))? true : false, businessTestFile);
	}
	//-- BDD Test : addDatingSpecsBDD -------------------------------------------------------------------------------------------
	@Test
	public void testAddDatingSpecsBDD() throws Exception
	{
		final int count[] = new int[1];
		
        DatingSpecsDTO datingSpecsDTO = DatingUtility.convertToDatingSpecsDTO(MasterData.getDatingSpecsDetails());
		
		Mockito.when(datingService.saveDatingSpecs(datingSpecsDTO)).then(new Answer<DatingSpecsDTO>() {
			@Override
			public DatingSpecsDTO answer(InvocationOnMock invocation) throws Throwable {
				System.out.println("Called : testAddDatingSpecsBDD");
				count[0]++;
				return datingSpecsDTO;
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/dating/addDatingSpecs")
				.content(MasterData.asJsonString(datingSpecsDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);	
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(count[0]);
		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
	}	
	
	//---------------------------------------------------------------------------------------------------------------------------
	//				2. Testing Rest End Point - /dating/deleteDataingSpecs/{userId}
	//-- Test 2 : deleteDataingSpecs --------------------------------------------------------------------------------------------
	@Test
	public void testDeleteDataingSpecsByUserId() throws Exception
	{
        DatingSpecsDTO datingSpecsDTO = DatingUtility.convertToDatingSpecsDTO(MasterData.getDatingSpecsDetails());
        Long userId = datingSpecsDTO.getUserId();
       
		Mockito.when(datingService.deleteDatingSpecs(userId)).thenReturn(datingSpecsDTO);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/dating/deleteDataingSpecs/" + userId)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);				
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(),	result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(datingSpecsDTO))? true : false, businessTestFile);
	}
	//-- BDD Test : deleteProfileBDD --------------------------------------------------------------------------------------------
	@Test
	public void testDeleteDataingSpecsByUserIdBDD() throws Exception 
	{
		final int count[] = new int[1];
	
        DatingSpecsDTO datingSpecsDTO = DatingUtility.convertToDatingSpecsDTO(MasterData.getDatingSpecsDetails());
        Long userId = datingSpecsDTO.getUserId();
	
		Mockito.when(datingService.deleteDatingSpecs(userId)).then(new Answer<DatingSpecsDTO>() {
			@Override
			public DatingSpecsDTO answer(InvocationOnMock invocation) throws Throwable {
				System.out.println("Called : testDeleteDatingSpecsByUserIdBDD");
				count[0]++;
				return datingSpecsDTO;
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/dating/deleteDatingSpecs/" + userId)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());
		System.out.println(count[0]);
		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
	}
	
	//---------------------------------------------------------------------------------------------------------------------------
	//				3. Testing Rest End Point - /dating/getDatingSpecsById/{userId}
	//-- Test 3 : getDatingSpecsById --------------------------------------------------------------------------------------------
	@Test
	public void testFindDatingSpecsById() throws Exception
	{
        DatingSpecsDTO datingSpecsDTO = DatingUtility.convertToDatingSpecsDTO(MasterData.getDatingSpecsDetails());
        Long userId = datingSpecsDTO.getUserId();
        
		Mockito.when(datingService.getDatingSpecsById(userId)).thenReturn(datingSpecsDTO);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dating/getDatingSpecsById/" + userId)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),	result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(datingSpecsDTO))? true : false, businessTestFile);		
	}
	//-- BDD Test : getDatingSpecsByIdBDD ---------------------------------------------------------------------------------------
	@Test
	public void testFindDatingSpecsByIdBDD() throws Exception 
	{
		final int count[] = new int[1];
		
        DatingSpecsDTO datingSpecsDTO = DatingUtility.convertToDatingSpecsDTO(MasterData.getDatingSpecsDetails());
        Long userId = datingSpecsDTO.getUserId();

		Mockito.when(datingService.getDatingSpecsById(userId)).then(new Answer<DatingSpecsDTO>() {
			@Override
			public DatingSpecsDTO answer(InvocationOnMock invocation) throws Throwable {
				System.out.println("Called : testFindDatingSpecsByIdBDD");
				count[0]++;
				return datingSpecsDTO;
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dating/getDatingSpecsById/" + userId)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(count[0]);
		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);			
	}	

	//---------------------------------------------------------------------------------------------------------------------------
	//				4. Testing Rest End Point - /user/listAllDatingSpecs
	//-- Test 4 : listAllDatingSpecs --------------------------------------------------------------------------------------------
	/*
	 * Description : This test is to perform view all DatingSpecs from database
	 */
	@Test 
	public void testFindAllDatingSpecs() throws Exception 
	{ 
		List<DatingSpecsDTO> list = DatingUtility.convertToDatingSpecsDtoList(MasterData.getAllDatingSpecs());
			
		Mockito.when(datingService.getAllDatingSpecs()).thenReturn(list);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dating/listAllDatingSpecs")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(MasterData.asJsonString(list));
		yakshaAssert(currentTest(), (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(list))? "true" : "false"),	businessTestFile);
	}
	//-- BDD Test : listAllDatingSpecsBDD ------------------------------------------------------------------------------------------
	@Test
	public void testFindAllDatingSpecsBDD() throws Exception 
	{
		final int count[] = new int[1];

		List<DatingSpecsDTO> list = DatingUtility.convertToDatingSpecsDtoList(MasterData.getAllDatingSpecs());

		Mockito.when(datingService.getAllDatingSpecs()).then(new Answer<List<DatingSpecsDTO>>() {
			@Override
			public List<DatingSpecsDTO> answer(InvocationOnMock invocation) throws Throwable {
				System.out.println("Called : testFindAllProfilesBDD");
				count[0]++;
				return list;
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dating/listAllDatingSpecs")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(count[0]);
		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
	}	
}