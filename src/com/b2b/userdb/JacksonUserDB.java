package com.b2b.userdb;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.b2b.exchnage.B2BConstants;
import com.b2b.exchnage.B2BExchangeServer;
import com.b2b.exchnage.B2BServerPropertiesManager;
import com.b2b.exchnage.SFTPUserProfile;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @author harip
 *
 */

public class JacksonUserDB {

	// private static ArrayList<SFTPUserProfile> users = new ArrayList<>();
	private static ConcurrentHashMap<String, SFTPUserProfile> users = new ConcurrentHashMap<>();

	private static final Logger log = LoggerFactory.getLogger(JacksonUserDB.class.getName());

	public static ConcurrentHashMap<String, SFTPUserProfile> getUserProfiles() {
		return users;
	}

	public static void addUser(SFTPUserProfile user1) {
		users.put(user1.getUserid(), user1);
		persistUserProfiles();
	}

	public static void addUser(String userid, String homefolder, String password, String[] pubkeys) {
		if (userid == null || homefolder == null) {
			log.error("User id and homefolder cannot be null " + " userid=" + userid + " homefolder " + homefolder);
			return;
		}
		SFTPUserProfile user1 = new SFTPUserProfile();
		user1.setUserid(userid);
		user1.setHomeFolder(homefolder);
		if (password != null)
			user1.setPassword(CryptUtl.encrypt("hello", "idievarikitelustidi"));
		// String keys1[]= {"key1","key2"};
		if (pubkeys != null && pubkeys.length > 0)
			user1.setPublickeys(new String[] { "key1", "key2" });
		users.put(userid, user1);
		persistUserProfiles();

	}

	public static void initUserProfiles() {
		// ArrayList<SFTPUserProfile> users = new ArrayList<>();
		SFTPUserProfile user1 = new SFTPUserProfile();
		user1.setUserid("venu100");
		user1.setHomeFolder("C:\\Users\\harip\\SFTP101");
		user1.setPassword(CryptUtl.encrypt("hello", "idievarikitelustidi"));
		// String keys1[]= {"key1","key2"};
		user1.setPublickeys(new String[] { "key1", "key2" });
		users.put("venu100", user1);

		SFTPUserProfile user2 = new SFTPUserProfile();
		user2.setUserid("venu101");
		user2.setHomeFolder("C:\\Users\\harip\\SFTP101");
		user2.setPassword(CryptUtl.encrypt("hello43", "idievarikitelustidi"));

		user2.setPublickeys(new String[] { "key3", "key4" });
		users.put("venu101", user2);
		persistUserProfiles();
	}

	public static void persistUserProfiles() {

		ObjectMapper mapper = new ObjectMapper();
		// ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

		// Java object to JSON file

		try {
			System.out.println(B2BServerPropertiesManager.get(B2BConstants.USERS_JSON, "users.json"));

			Collection<SFTPUserProfile> collection = users.values();
			SFTPUserProfile[] userArray = collection.toArray(new SFTPUserProfile[0]);

			mapper.writeValue(new File(B2BServerPropertiesManager.get(B2BConstants.USERS_JSON, "users.json")),
					userArray);
			
			java.lang.reflect.Type collectionType = new TypeToken<ConcurrentHashMap<String,SFTPUserProfile>>(){}.getType();
			

			// mapper.updateValue(valueToUpdate, overrides)
			// mapper.updateValue(valueToUpdate, overrides)

		} catch (IOException ex) {
			// TODO Auto-generated catch block
			log.error("Unable to write to file", ex);
		}

	}
	
	public static void loadProfileConcurrentHashMap()
	{
		
		java.lang.reflect.Type collectionType = new TypeToken<ConcurrentHashMap<String,SFTPUserProfile>>(){}.getType();
		ObjectMapper mapper = new ObjectMapper();
		users= mapper.readValue("new File("C:\\Users\\harip\\eclipse-workspace\\B2BExchange\\users.json"), collectionType)
		
	}

	public static void loadUserProfiles() {
		ObjectMapper mapper = new ObjectMapper();
		// JSON file to Java object
		// SFTPUserProfile obj[] = mapper.readValues(new File("users.json"),
		// SFTPUserProfile.class);

		try {
			@SuppressWarnings("unchecked")
			SFTPUserProfile[] userArray = mapper.readValue(
					new File(B2BServerPropertiesManager.get(B2BConstants.USERS_JSON, "users.json")),
					SFTPUserProfile[].class);
			users.clear();
			for (SFTPUserProfile user : userArray) {
				users.put(user.getUserid(), user);
			}

			// SFTPUserProfile s = (SFTPUserProfile)obj1.entrySet();
			// System.out.println(s.getPassword());

			// users = new ArrayList<SFTPUserProfile>(Arrays.asList(myObjects));

			// mapper.writerWithDefaultPrettyPrinter().writeValueAsString(myObjects);

			// System.out.println(myObjects[0]);
			// System.out.println(myObjects[1]);

			// myObjects.get("venu101");

			// System.out.println(" password " + user1.getPassword());

			// System.out.println(" password " + CryptUtl.decrypt(password,
			// "idievarikitelustidi"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Users Size " + users.size());
		System.out.println("Users Size " + users.get("venu101"));

	}

	public static void main(String[] args) {
		initUserProfiles();
		loadUserProfiles();
	}

}
