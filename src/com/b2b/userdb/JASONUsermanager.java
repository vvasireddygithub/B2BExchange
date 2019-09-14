package com.b2b.userdb;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.b2b.exchnage.SFTPUserProfile;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JASONUsermanager {

	private static final int String = 0;

	public static void initUserDB() {
		ArrayList<SFTPUserProfile> users = new ArrayList<>();
		SFTPUserProfile user1 = new SFTPUserProfile();
		user1.setUserid("venu100");
		user1.setHomeFolder("C:\\Users\\harip\\SFTP101");
		user1.setPassword("hello");
		//String keys1[]= {"key1","key2"};
		user1.setPublickeys(new String[] {"key1","key2"});
		users.add(user1);
		SFTPUserProfile user2 = new SFTPUserProfile();
		user2.setUserid("venu100");
		user2.setHomeFolder("C:\\Users\\harip\\SFTP101");
		user2.setPassword("hello");
		
		
		
		user2.setPublickeys(new String[] {"key3","key4"});
		users.add(user2);
		
		ObjectMapper mapper = new ObjectMapper();

		// Java object to JSON file
		try {
			mapper.writeValue(new File("users.json"), users);
			//mapper.updateValue(valueToUpdate, overrides)
			//mapper.updateValue(valueToUpdate, overrides)
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static void readUsers() {
		ObjectMapper mapper = new ObjectMapper();
		//JSON file to Java object
		//SFTPUserProfile obj[] = mapper.readValues(new File("users.json"), SFTPUserProfile.class);
		
		try {
			SFTPUserProfile[] myObjects = mapper.readValue(new File("users.json"), SFTPUserProfile[].class);
			
			mapper.writerWithDefaultPrettyPrinter().writeValueAsString(myObjects);
			
			System.out.println(myObjects[0]);
			System.out.println(myObjects[1]);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
