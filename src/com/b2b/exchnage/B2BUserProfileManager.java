package com.b2b.exchnage;

import java.nio.file.Paths;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.sshd.common.file.virtualfs.VirtualFileSystemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.b2b.userdb.JASONUsermanager;

public class B2BUserProfileManager {
	private static final Logger log = LoggerFactory.getLogger(B2BUserProfileManager.class.getName());

	private static final ConcurrentHashMap<String, String> userPasswords = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, String[]> userPublicKeys = new ConcurrentHashMap<>();

	private static VirtualFileSystemFactory vfSysFactory = null;

	public static void setVFSFactory(VirtualFileSystemFactory vfSysFactor) {
		B2BUserProfileManager.vfSysFactory = vfSysFactor;
		B2BUserProfileManager.initializeUserDatabase();
	}

	public static void setUserPassword(String userid, String password) {
		if (userid == null || password == null)
			log.error("Either userid or password is null " + userid);
		userPasswords.put(userid, password);

	}

	public static void setUserHomeFolder(String userid, String homeFolder) {
		if (userid == null || homeFolder == null)
			log.error("Either userid or homeFolder are null " + userid);
		vfSysFactory.setUserHomeDir(userid, Paths.get(homeFolder));

	}

	public static boolean checkpassword(String userid, String password) {
		if (userPasswords.get(userid).equals(password))
			return true;
		else
			return false;
	}

	public static void setUserPublicKeys(String userid, String[] pubkeyFileNames) {
		if (userid == null || pubkeyFileNames == null)
			log.error("Either userid or homeFolder are null " + userid);
		userPublicKeys.put(userid, pubkeyFileNames);
	}

	public static void initializeUserDatabase() {
		setUserHomeFolder("venu100", "C:\\Users\\harip\\SFTP");
		setUserHomeFolder("venu101", "C:\\Users\\harip\\SFTP101");
		setUserHomeFolder("venu102", "C:\\Users\\harip\\SFTP102");
		setUserHomeFolder("venu103", "C:\\Users\\harip\\SFTP103");
		setUserHomeFolder("venu104", "C:\\Users\\harip\\SFTP104");
		setUserHomeFolder("venu105", "C:\\Users\\harip\\SFTP105");

		setUserPassword("venu100", "venu100");
		setUserPassword("venu101", "venu101");
		setUserPassword("venu102", "venu102");
		setUserPassword("venu103", "venu104");
		setUserPassword("venu105", "venu105");
	}
	public static void main(String... args)
	{
		JASONUsermanager.initUserDB();
		JASONUsermanager.readUsers();
		
	}
	

}
