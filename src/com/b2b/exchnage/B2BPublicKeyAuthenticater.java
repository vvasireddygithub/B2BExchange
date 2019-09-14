package com.b2b.exchnage;

import java.security.PublicKey;

import org.apache.sshd.server.auth.AsyncAuthException;
import org.apache.sshd.server.auth.pubkey.PublickeyAuthenticator;
import org.apache.sshd.server.session.ServerSession;

public class B2BPublicKeyAuthenticater implements PublickeyAuthenticator {

	@Override
	public boolean authenticate(String username, PublicKey key, ServerSession session) throws AsyncAuthException {
		// TODO Auto-generated method stub
		return true;
	}

}
