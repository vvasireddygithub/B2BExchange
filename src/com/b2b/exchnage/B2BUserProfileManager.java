package com.b2b.exchnage;

import java.nio.file.Paths;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.sshd.common.file.virtualfs.VirtualFileSystemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.b2b.userdb.CryptUtl;
import com.b2b.userdb.JacksonUserDB;

public class B2BUserProfileManager {
	private static final Logger log = LoggerFactory.getLogger(B2BUserProfileManager.class.getName());

	//private static final ConcurrentHashMap<String, String> userPasswords = new ConcurrentHashMap<>();
	//private static final ConcurrentHashMap<String, String[]> userPublicKeys = new ConcurrentHashMap<>();

	private static VirtualFileSystemFactory vfSysFactory = null;
	private static ConcurrentHashMap<String, SFTPUserProfile> userprofiles = null;

	public static void setVFSFactory(VirtualFileSystemFactory vfSysFactor) {
		B2BUserProfileManager.vfSysFactory = vfSysFactor;
		//JacksonUserDB.loadUserProfiles();
		JacksonUserDB.loadProfileConcurrentHashMap();
		userprofiles = JacksonUserDB.getUserProfiles();
		setHomeFolders();
	}

	public static void setUserPassword(String userid, String password) {
		if (userid == null || password == null)
			log.error("Either userid or password is null " + userid);
		
		userprofiles.get(userid).setPassword(password);
		//userPasswords.put(userid, password);

	}

	public static void setUserHomeFolder(String userid, String homeFolder) {
		if (userid == null || homeFolder == null)
			log.error("Either userid or homeFolder are null " + userid);
		vfSysFactory.setUserHomeDir(userid, Paths.get(homeFolder));

	}

	public static boolean checkpassword(String userid, String password) {
	//	log.debug(" CryptUtl.decrypt(userprofiles.get(userid).getPassword(),\"idievarikitelustidi\") " + CryptUtl.decrypt(userprofiles.get(userid).getPassword(),"idievarikitelustidi"));
		if (CryptUtl.decrypt(userprofiles.get(userid).getPassword(),"idievarikitelustidi").equals(password))
			return true;
		else
			return false;
	}

	public static void setUserPublicKeys(String userid, String[] pubkeyFileNames) {
		if (userid == null || pubkeyFileNames == null)
			log.error("Either userid or homeFolder are null " + userid);
		userprofiles.get(userid).setPublickeys(pubkeyFileNames);
	}

	public static void setHomeFolders() {

		for (Entry<String, SFTPUserProfile> user : userprofiles.entrySet()) {

			setUserHomeFolder(((String) user.getKey()), user.getValue().getHomeFolder());
		}

	}

	public static void main(String... args) {
		// JacksonUsermanager.initUserDB();
		// JacksonUsermanager.readUsers();

	}

}
