package com.b2b.exchnage;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.sshd.client.auth.hostbased.UserAuthHostBased;
import org.apache.sshd.common.file.virtualfs.VirtualFileSystemFactory;
import org.apache.sshd.common.io.IoServiceFactoryFactory;
import org.apache.sshd.common.io.mina.MinaServiceFactoryFactory;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.auth.keyboard.DefaultKeyboardInteractiveAuthenticator;
import org.apache.sshd.server.config.keys.AuthorizedKeysAuthenticator;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B2BExchangeServer {
	protected final Logger log=LoggerFactory.getLogger(B2BExchangeServer.class.getName());

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		SshServer sshd = SshServer.setUpDefaultServer();
		sshd.setPort(10022);
		sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(Paths.get("C:\\Apps\\sshd\\hostkey.ser")));
		
		System.setProperty(IoServiceFactoryFactory.class.getName(),
			MinaServiceFactoryFactory.class.getName());
		
		System.out.println(IoServiceFactoryFactory.class.getName());
		sshd.setHost("DESKTOP-7591820");
		sshd.setPasswordAuthenticator(new B2BPasswordAuthenticater());
		sshd.setKeyboardInteractiveAuthenticator(new DefaultKeyboardInteractiveAuthenticator());
		//sshd.setPublickeyAuthenticator(new AuthorizedKeysAuthenticator(Paths.get("C:\\Apps\\sshd\\authorized_keys")));
		sshd.setHostBasedAuthenticator(new B2BHostBasedAuthenticater());
		sshd.setPublickeyAuthenticator(new B2BPublicKeyAuthenticater());
		sshd.setFileSystemFactory(new VirtualFileSystemFactory());
		//help
		//sshd.close();
       //status
		
		/*
		 * System.setProperty(IoServiceFactoryFactory.class.getName(),
		 * Nio2ServiceFactoryFactory.class.getName());
		 */
		
		//sshd.setIoServiceFactoryFactory(new Nio2ServiceFactoryFactory());
		
		//sshd.setIoServiceFactoryFactory(new B2BExchangeServiceFactory());
		
		
		//System.clearProperty(IoServiceFactoryFactory.class.getName());
		
		//setIoServiceFactoryFactory(new DefaultIoServiceFactoryFactory());
		 
		sshd.start();

	}

}
