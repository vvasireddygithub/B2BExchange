package com.b2b.exchnage;

import java.security.PublicKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.cert.X509Certificate;
import java.util.List;

import org.apache.sshd.server.auth.hostbased.HostBasedAuthenticator;
import org.apache.sshd.server.session.ServerSession;

public class B2BHostBasedAuthenticater implements HostBasedAuthenticator {
	protected final Logger log=LoggerFactory.getLogger(B2BExchangeServer.class.getName());

	@Override
	public boolean authenticate(ServerSession session, String username, PublicKey clientHostKey,
			String clientHostName,
			String clientUsername, List<X509Certificate> certificates) {
		// TODO Auto-generated method stub
		log.debug("User name" + username);
		log.debug("User name" + clientHostName);
		
		return false;
	}

}
