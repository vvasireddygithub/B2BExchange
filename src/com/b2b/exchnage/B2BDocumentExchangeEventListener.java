package com.b2b.exchnage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.sshd.server.session.ServerSession;
import org.apache.sshd.server.subsystem.sftp.AbstractSftpEventListenerAdapter;
import org.apache.sshd.server.subsystem.sftp.Handle;

public class B2BDocumentExchangeEventListener extends AbstractSftpEventListenerAdapter {
	 @Override
	    public void opening(ServerSession session, String remoteHandle, Handle localHandle) throws IOException {
	        if (log.isTraceEnabled()) {
	            Path path = localHandle.getFile();
	            log.trace("opening(" + session + ")[" + remoteHandle + "] " + (Files.isDirectory(path) ? "directory" : "file") + " " + path);
	        }
	    }

	    @Override
	    public void open(ServerSession session, String remoteHandle, Handle localHandle) {
	        if (log.isTraceEnabled()) {
	            Path path = localHandle.getFile();
	            log.trace("open(" + session + ")[" + remoteHandle + "] " + (Files.isDirectory(path) ? "directory" : "file") + " " + path);
	        }
	    }

}
