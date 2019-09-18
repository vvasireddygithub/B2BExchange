package com.b2b.userdb;

import java.util.ArrayList;
import java.util.Collection;

import com.b2b.exchnage.SFTPUserProfile;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @author harip
 *
 */
public class GSONUserDB {
	public static void initGSONUserDB() {
		ArrayList<SFTPUserProfile> users = new ArrayList<>();
		SFTPUserProfile user1 = new SFTPUserProfile();
		user1.setUserid("venu100");
		user1.setHomeFolder("C:\\Users\\harip\\SFTP101");
		user1.setPassword("hello");
		//String keys1[]= {"key1","key2"};
		user1.setPublickeys(new String[] {"key1","key2"});
		users.add(user1);
		
		SFTPUserProfile user2 = new SFTPUserProfile();
		user2.setUserid("venu101");
		user2.setHomeFolder("C:\\Users\\harip\\SFTP101");
		user2.setPassword("hello");
		
		
		
		user2.setPublickeys(new String[] {"key3","key4"});
		users.add(user2);
		
		Gson gson = new Gson();
		String myjson = gson.toJson(users);
		System.out.println(myjson);
		
		
		System.out.println(gson.toJson(1));            // ==> 1
		System.out.println(gson.toJson("abcd"));       // ==> "abcd"
		System.out.println(gson.toJson(new Long(10))); // ==> 10
		int[] values = { 1 };
		System.out.println(gson.toJson(values));       // ==> [1]
		
		
		// Deserialization
		java.lang.reflect.Type collectionType = new TypeToken<Collection<SFTPUserProfile>>(){}.getType();
		String jsonString=
				"[{\"userid\":\"venu100\",\"password\":\"hello\",\"publickeys\":[\"key1\",\"key2\"],\"homeFolder\":\"C:\\\\Users\\\\harip\\\\SFTP101\"},"
				+ "{\"userid\":\"venu101\",\"password\":\"hello\",\"publickeys\":[\"key3\",\"key4\"],\"homeFolder\":\"C:\\\\Users\\\\harip\\\\SFTP101\"}]";
		ArrayList<SFTPUserProfile> users100 = gson.fromJson(jsonString, (java.lang.reflect.Type) collectionType);
		
		System.out.println("*****\r\n" + users100.toString());
		System.out.println("*****\r\n" + users100.size());
		
		
		
		
	}

}
