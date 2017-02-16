package com.vmware.transformer.utils;

import java.io.IOException;
import org.apache.log4j.Logger;
import ch.ethz.ssh2.Connection;

public class SSHConnection {
	public Connection conn = null;
	
	private Logger log = Log4jInstance.getLoggerInstance();
	
	boolean authenticationStatus = false;
	
	public SSHConnection(String hostName, String userName, String password)
	{
		this.conn = new Connection(hostName);
		try {
			conn.connect();
			authenticationStatus = conn.authenticateWithPassword(userName, password);
			if(authenticationStatus){
				log.info("Connect to " + hostName +" successfully");
			}else{
				log.error("failed to connect: " + hostName);
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
}
