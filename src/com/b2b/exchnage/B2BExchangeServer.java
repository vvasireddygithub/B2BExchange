package com.b2b.exchnage;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;

//import org.apache.log4j.BasicConfigurator;
import org.apache.sshd.common.file.virtualfs.VirtualFileSystemFactory;
import org.apache.sshd.common.io.IoServiceFactoryFactory;
import org.apache.sshd.common.io.mina.MinaServiceFactoryFactory;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.auth.UserAuthFactory;
import org.apache.sshd.server.auth.hostbased.StaticHostBasedAuthenticator;
import org.apache.sshd.server.auth.keyboard.DefaultKeyboardInteractiveAuthenticator;
import org.apache.sshd.server.auth.pubkey.UserAuthPublicKeyFactory;
import org.apache.sshd.server.config.keys.AuthorizedKeysAuthenticator;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.scp.ScpCommandFactory;
import org.apache.sshd.server.subsystem.sftp.SftpSubsystemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

public class B2BExchangeServer {
	protected final Logger log = LoggerFactory.getLogger(B2BExchangeServer.class.getName());

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//System.setProperty("log4j.configuration", "C:\\Users\\harip\\eclipse-workspace\\B2BExchange\\log4j.properties");
		
		
		System.setProperty("java.util.logging.config.file", "C:\\\\Users\\\\harip\\\\eclipse-workspace\\\\B2BExchange\\\\jdkutillogging.properties");
				
		 
		// System.out.println( System.getProperty("log4j.configuration"));
		 
		// BasicConfigurator.configure();
		
		String filePath = args[0]; // Properties files pth
		B2BServerPropertiesManager.LoadProperties(filePath);
		SshServer sshd = SshServer.setUpDefaultServer();
	
		sshd.setPort(Integer.parseInt(B2BServerPropertiesManager.get(B2BServerPropertiesManager.server_port, "10022")));
		
		
		sshd.setHost(B2BServerPropertiesManager.get(B2BServerPropertiesManager.server_host,"localhost"));
		sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(Paths.get(B2BServerPropertiesManager.get(
				B2BServerPropertiesManager.hostkeyprovider,"hostkey.ser"))));

		System.setProperty(IoServiceFactoryFactory.class.getName(), MinaServiceFactoryFactory.class.getName());

		//System.out.println(IoServiceFactoryFactory.class.getName());
		
		sshd.setPasswordAuthenticator(new B2BPasswordAuthenticater());
		sshd.setKeyboardInteractiveAuthenticator(new DefaultKeyboardInteractiveAuthenticator());
		sshd.setPublickeyAuthenticator(new
		AuthorizedKeysAuthenticator(Paths.get("C:\\Apps\\sshd\\authorized_keys")));
	    sshd.setHostBasedAuthenticator(new B2BHostBasedAuthenticater());

		//sshd.setHostBasedAuthenticator(new StaticHostBasedAuthenticator(true));

		//sshd.setPublickeyAuthenticator(new B2BPublicKeyAuthenticater());

		//UserAuthFactory userAuthFactory = new UserAuthPublicKeyFactory();
		//sshd.setUserAuthFactories(Collections.singletonList(userAuthFactory));
		
		
		// Sets the logging level to INFO
		Logger root = (Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		//root.(Level.TRACE);
		

		String rootFileDir = "C:\\Users\\sshdusers";
		VirtualFileSystemFactory vfSysFactory = new VirtualFileSystemFactory();
		vfSysFactory.setDefaultHomeDir(Paths.get(rootFileDir));
		
		vfSysFactory.setUserHomeDir("venu100", Paths.get("C:\\Users\\harip\\SFTP"));
		
		sshd.setFileSystemFactory(vfSysFactory);

	
		ScpCommandFactory scpCommand = new ScpCommandFactory();
		sshd.setCommandFactory(scpCommand);

		SftpSubsystemFactory sftpFactory = new SftpSubsystemFactory();
		sshd.setSubsystemFactories(Collections.singletonList(sftpFactory));

		// sshd.setSubsystemFactories( Collections.<NamedFactory<Command>>singletonList(
		// new SftpSubsystemFactory() ) );

		// sshd.setSubsystemFactories(Arrays.<NamedFactory<Command>>asList(new
		// SftpSubsystemFactory()));

		// sshd.setCommandFactory(new ScpCommandFactory.Builder().withDelegate(new
		// ShellCommandFactory(sessionFactory)).build());
		// sshd.setSubsystemFactories(Arrays.<NamedFactory<org.apache.sshd.server.Command>>asList(new
		// SftpSubsystemFactory()));

		/*
		 * 
		 * List<NamedFactory<Command>> namedFactoryList = new
		 * ArrayList<NamedFactory<Command>>(); namedFactoryList.addAll(new
		 * SftpSubsystemFactory()); sshd.setSubsystemFactories(namedFactoryList);
		 * 
		 */

		// help
		// sshd.close();
		// status

		/*
		 * System.setProperty(IoServiceFactoryFactory.class.getName(),
		 * Nio2ServiceFactoryFactory.class.getName());
		 */

		// sshd.setIoServiceFactoryFactory(new Nio2ServiceFactoryFactory());

		// sshd.setIoServiceFactoryFactory(new B2BExchangeServiceFactory());

		// System.clearProperty(IoServiceFactoryFactory.class.getName());

		// setIoServiceFactoryFactory(new DefaultIoServiceFactoryFactory());

		sshd.start();

	}

}
