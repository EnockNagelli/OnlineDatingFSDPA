package com.iiht.dating.utilTestClass;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import org.hibernate.engine.jdbc.BlobProxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiht.dating.model.DatingSpecs;
import com.iiht.dating.model.Profile;
import com.iiht.dating.model.User;

public class MasterData 
{
	//---------------------------------------------------------------------------------------------------------------------------------
	public static User getUserDetails() 
	{
		User user = new User();
		
		user.setUserId((long)1);
		user.setFirstName("Praveen");
		user.setLastName("Kumar");
		user.setDateOfBirth(LocalDate.parse("2020-11-10"));
		user.setGender("Male");
		user.setAddress("Hyderabad");
		user.setLoginName("praveen");
		user.setPassword("kumar");
		
		return user;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public static Profile getProfileDetails() 
	{
		Profile profile = new Profile();
		
		profile.setUserId((long)1);
		
		InputStream inputStream = null;														// input stream of the upload file
        Part filePart = (Part) new File("E:\\Enock's Profiles\\Nexwave Photo.jpg");			// obtains the upload file part in this multipart request
		if (filePart != null) {
			try {
				inputStream = filePart.getInputStream();									// obtains input stream of the upload file
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}																				
		}
        //Blob photo = Hibernate.getLobCreator(sessionFactory.getCurrentSession()).createBlob(inputStream, 16177215);
        //org.springframework.web.util.NestedServletException: Request processing failed; nested exception is org.hibernate.HibernateException: Could not obtain transaction-synchronized Session for current thread

        Blob profileImage = BlobProxy.generateProxy(inputStream, 16177215);
	
		profile.setProfileImage(profileImage);
		profile.setContactNo((long)12345);
		profile.setEmail("abc@gmail.com");
		profile.setQualification("B.Tech");
		profile.setHobbies("aaaa");
		profile.setFoodHabits("bbbb");
		profile.setAboutMe("cccc");
		
		return profile;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public static DatingSpecs getDatingSpecsDetails() 
	{
		DatingSpecs dating = new DatingSpecs();
		
		dating.setUserId((long)1);
		dating.setReceiverId((long)2);
		dating.setDatingDate(LocalDate.parse("2020-12-20"));
		dating.setDatingTime(LocalTime.parse("09:00:00"));
		dating.setLocation("DatingPark");
		dating.setRequestStatus("Pending");
		dating.setDatingRequest("I am from Hyderabad");

		return dating;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public static List<User> getAllUsers() 
	{
		List<User> users = new ArrayList<User>();
		
		users.add(new User((long)1, "Praveen", "Kumar", LocalDate.parse("2020-11-10"), "Male", "Hyderabad", "praveen", "kumar"));
		users.add(new User((long)2, "Shekar", "Gupta", LocalDate.parse("2020-12-10"), "Male", "Mumbai", "shekar", "gupta"));
		users.add(new User((long)3, "Sneha", "Latha", LocalDate.parse("2020-12-10"), "Female", "Chennai", "sneha", "latha"));
		users.add(new User((long)4, "Rinkki", "Ford", LocalDate.parse("2020-12-10"), "Female", "New York", "rinkki", "ford"));

		return users;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public static List<DatingSpecs> getAllDatingSpecs(){
		
		List<DatingSpecs> dating = new ArrayList<DatingSpecs>();
		
		dating.add(new DatingSpecs((long)1, (long)3, LocalDate.parse("2020-12-20"), LocalTime.parse("09:00:00"), "aaaa", "bbbb", "cccc"));
		dating.add(new DatingSpecs((long)2, (long)4, LocalDate.parse("2020-12-20"), LocalTime.parse("09:00:00"), "aaaa", "bbbb", "cccc"));
		dating.add(new DatingSpecs((long)5, (long)7, LocalDate.parse("2020-12-20"), LocalTime.parse("09:00:00"), "aaaa", "bbbb", "cccc"));
		dating.add(new DatingSpecs((long)6, (long)8, LocalDate.parse("2020-12-20"), LocalTime.parse("09:00:00"), "aaaa", "bbbb", "cccc"));
		
		return dating;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}	
}