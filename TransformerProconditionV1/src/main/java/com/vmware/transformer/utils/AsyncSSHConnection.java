package com.vmware.transformer.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class AsyncSSHConnection implements Callable<String>{

	private Connection conn = null;
	private ThreadSharedData cmdFeedback = ThreadSharedData.getInstance();
	private String cmd = null;
	private Logger log = Log4jInstance.getLoggerInstance();
	private String markString = "";
	
	/**
	 * 
	 * @param cmd
	 * @param hostName
	 * @param userName
	 * @param password
	 * @param markString
	 */
	public AsyncSSHConnection(String cmd, String hostName, String userName, String password, String markString) {
		SSHConnection sshc = new SSHConnection(hostName, userName, password);
		this.cmd = cmd + "\n";
		conn = sshc.conn;
		this.markString = markString;
	}


	@SuppressWarnings("resource")
	private String getNimbusStandOutputString(){
		InputStream stdOut = null; 
		try {

			Session session = conn.openSession(); 
			OutputStream out = session.getStdin();
			session.requestDumbPTY();
			session.startShell();
log.info("SSH stand output message: " + cmd);			
			out.write(cmd.getBytes());
			stdOut = new StreamGobbler(session.getStdout()); 

			BufferedReader brs = new BufferedReader(new InputStreamReader(stdOut));
			String linetemp = null;
			
			//Clean the cmdFeedback.value and cmdFeedback.completed
			cmdFeedback.value = "";
			cmdFeedback.completed = false;
			
			//get the thumb print of host
			while((linetemp =brs.readLine()) != null){ 
System.out.println("Into the loop!");
			    	cmdFeedback.value = cmdFeedback.value + linetemp;
			    	log.info("SSH stand output message: " + linetemp);
			    	if(linetemp.contains(this.markString)){
			    		log.info("Get the " + this.markString + " successfully!");
			    		cmdFeedback.completed = true;
			    		break;
			    	}
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return cmdFeedback.value;
	}


	@Override
	public String call(){	
		return this.getNimbusStandOutputString();
	}
}
