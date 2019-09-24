package com.b2b.userdb;

import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

import com.b2b.exchnage.B2BConstants;
import com.b2b.exchnage.B2BServerPropertiesManager;

public class DBUtil {
	public static void persistUserDB(String jason)
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

}
