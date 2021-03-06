package com.b2b.exchnage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *  This class loads the SSH/SFTP Server properties
 * @author harip
 *
 */

public class B2BServerPropertiesManager {
	

	private static final Logger log = LoggerFactory.getLogger(B2BServerPropertiesManager.class);

	private static final Properties props = new Properties();

	public static void LoadProperties(String propertiesFilePath) {
		try {
			Objects.requireNonNull(propertiesFilePath, " propertiesFilePath is null ");
			props.load(Files.newBufferedReader(Paths.get(propertiesFilePath)));
		} catch (IOException ex) {
			log.error(" Unable to load properties from " + propertiesFilePath, ex);
		} 
	}
	public static final String get(String key,String defaultvalue) {
		return props.getProperty(key,defaultvalue);
	}

}
