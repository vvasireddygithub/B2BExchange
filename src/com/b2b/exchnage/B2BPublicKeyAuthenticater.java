package com.b2b.exchnage;

import java.security.PublicKey;
import java.util.Base64;

import org.apache.sshd.server.auth.AsyncAuthException;
import org.apache.sshd.server.auth.pubkey.PublickeyAuthenticator;
import org.apache.sshd.server.session.ServerSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B2BPublicKeyAuthenticater implements PublickeyAuthenticator {
	
	protected final Logger log = LoggerFactory.getLogger(B2BPublicKeyAuthenticater.class.getName());

	@Override
	public boolean authenticate(String username, PublicKey key, ServerSession session) throws AsyncAuthException {
		// TODO Auto-generated method stub
		
		
		//ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQC8tYgWss/VtmJT6888FINswGcMPE3eYFTfc/+vjqpk/kCjPboUDqshuQWOptupb89syjjklmzPspxc4JcQPzfmAv4KGrze6W0SEgEWD5aIpx/tSJ4bP4vEwsdccx/7VxWpZxGuVe8eaI/4Q2616stcQqq6HuPSpSUlkqfMGf+4cqOxPPDfqXFjL9i61YW3RkC3NPNOlhGiPyxsrdB4f+H8YR2MbRreauvMOvyjPP8x8eOw2EE7EYNRpdWup8d0IRC9jpKQ5MaVlo55ZnpxmxEcy1nZtJGz7FfVVUrL1HhC09jr1t02XsWi7G0GTVsbLgQqgQWm/IrYgcfqL4OD2muj harip@DESKTOP-7591820
		log.info(" Encoded key " + Base64.getEncoder().encodeToString(key.getEncoded()));
		
		log.info(" key algorithm " + key.getAlgorithm() + " Format " + key.getFormat());
		
	
		
		
		//ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQC8tYgWss/VtmJT6888FINswGcMPE3eYFTfc/+vjqpk/kCjPboUDqshuQWOptupb89syjjklmzPspxc4JcQPzfmAv4KGrze6W0SEgEWD5aIpx/tSJ4bP4vEwsdccx/7VxWpZxGuVe8eaI/
		//4Q2616stcQqq6HuPSpSUlkqfMGf+4cqOxPPDfqXFjL9i61YW3RkC3NPNOlhGiPyxsrdB4f+H8YR2MbRreauvMOvyjPP8x8eOw2EE7EYNRpdWup8d0IRC9jpKQ5MaVlo55ZnpxmxEcy1nZtJGz7FfVVUrL1HhC09jr1t02XsWi7G0GTVsbLgQqgQWm
		//IrYgcfqL4OD2muj harip@DESKTOP-7591820
		return false;
	}

}
