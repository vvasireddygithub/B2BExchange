package com.b2b.userdb;

import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import com.b2b.exchnage.B2BConstants;
import com.b2b.exchnage.B2BServerPropertiesManager;
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
		//ArrayList<SFTPUserProfile> users = new ArrayList<>();
		ConcurrentHashMap<String, SFTPUserProfile> users = new ConcurrentHashMap<>();
		SFTPUserProfile user1 = new SFTPUserProfile();
		user1.setUserid("venu101");
		user1.setHomeFolder("C:\\Users\\harip\\SFTP101");
		user1.setPassword("hello");
		//String keys1[]= {"key1","key2"};
		user1.setPublickeys(new String[] {"key1","key2"});
		//users.add(user1);
		
		users.put("venu101",user1);
		
		
		SFTPUserProfile user2 = new SFTPUserProfile();
		user2.setUserid("venu102");
		user2.setHomeFolder("C:\\Users\\harip\\SFTP102");
		user2.setPassword("hello");
		
		
		
		user2.setPublickeys(new String[] {"key3","key4"});
		//users.add(user2);
		
		users.put("venu102",user2);
		
		
		SFTPUserProfile user3 = new SFTPUserProfile();
		user3.setUserid("venu103");
		user3.setHomeFolder("C:\\Users\\harip\\SFTP103");
		user3.setPassword("hell103");
		
		
		
		user3.setPublickeys(new String[] {"key3","key4"});
		//users.add(user2);
		
		users.put("venu103",user3);
		
		
		Gson gson = new Gson();
		String myjson = gson.toJson(users);
		System.out.println(myjson);
		
		persistUserDB(myjson);
		
		System.out.println(gson.toJson(1));            // ==> 1
		System.out.println(gson.toJson("abcd"));       // ==> "abcd"
		System.out.println(gson.toJson(new Long(10))); // ==> 10
		int[] values = { 1 };
		System.out.println(gson.toJson(values));       // ==> [1]
		
		
		// Deserialization
		//java.lang.reflect.Type collectionType = new TypeToken<Collection<SFTPUserProfile>>(){}.getType();
		
		java.lang.reflect.Type collectionType = new TypeToken<ConcurrentHashMap<String,SFTPUserProfile>>(){}.getType();
		
		
		
		
		//String jsonString=
		//		"[{\"userid\":\"venu100\",\"password\":\"hello\",\"publickeys\":[\"key1\",\"key2\"],\"homeFolder\":\"C:\\\\Users\\\\harip\\\\SFTP101\"},"
		//		+ "{\"userid\":\"venu101\",\"password\":\"hello\",\"publickeys\":[\"key3\",\"key4\"],\"homeFolder\":\"C:\\\\Users\\\\harip\\\\SFTP101\"}]";
		
		//ArrayList<SFTPUserProfile> users100 = gson.fromJson(myjson, (java.lang.reflect.Type) collectionType);
		
		ConcurrentHashMap<String,SFTPUserProfile> users100 = gson.fromJson(myjson, (java.lang.reflect.Type) collectionType);
					
		
		System.out.println("*****\r\n" + users100.toString());
		System.out.println("*****\r\n" + users100.size());
		
		
		
		
	}
	
	private static void persistUserDB(String jason)
	{
		String userDBfileName = B2BServerPropertiesManager.get(B2BConstants.USERS_JSON, "C:\\\\Users\\\\harip\\\\eclipse-workspace\\\\B2BExchange\\\\users_json");
		
		
		 boolean bLocked = false;
		try (RandomAccessFile fis = new RandomAccessFile(userDBfileName, "rw")) {
			FileLock lck = fis.getChannel().lock();
			
			if (lck.isValid()) {
				fis.seek(0l);
				fis.writeChars(jason);
				//fis.writeChars(jason);
				
			}
			lck.release();
		} catch (Exception ex) {
			      bLocked = true;
			      ex.printStackTrace();
			    }
		
	}
	
	
	public static void loadUserprofiles() {
		
	}
	public static void main(String... args)
	{
		//initGSONUserDB();
		initGSONUserDB();
	}
}
