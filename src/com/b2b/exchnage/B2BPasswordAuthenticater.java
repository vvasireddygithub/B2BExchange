package com.b2b.exchnage;


import org.apache.sshd.server.auth.AsyncAuthException;
import org.apache.sshd.server.auth.password.PasswordAuthenticator;
import org.apache.sshd.server.auth.password.PasswordChangeRequiredException;
import org.apache.sshd.server.session.ServerSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class B2BPasswordAuthenticater  implements PasswordAuthenticator {
	protected final Logger log=LoggerFactory.getLogger(B2BPasswordAuthenticater.class.getName());

	@Override
	public boolean authenticate(String username, String password, ServerSession session)
			throws PasswordChangeRequiredException, AsyncAuthException {
		// TODO Auto-generated method stub
		log.info("username:" + username + "," + "operation:" + "LOGIN_USING_PASSWORD" + "," + "remoteip:" + session.getRemoteAddress());
		return true;
	}

}
