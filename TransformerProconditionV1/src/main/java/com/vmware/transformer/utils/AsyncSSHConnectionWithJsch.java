package com.vmware.transformer.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.concurrent.Callable;
import org.apache.log4j.Logger;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;



public class AsyncSSHConnectionWithJsch implements Callable<Object> {
	private ThreadSharedData cmdFeedback = ThreadSharedData.getInstance();
	private String HostName = null;
	private String usr = null;
	private String psword = null;
	private String cmd = null;
	private String markString = "";
	
	private Logger log = Log4jInstance.getLoggerInstance();
	
	/**
	 * 
	 * @param hostName
	 * @param userName
	 * @param pwd
	 * @param cmd
	 * @param markString
	 */
	AsyncSSHConnectionWithJsch(String hostName, String userName, String pwd, String cmd, String markString)
	{
		this.HostName = hostName;
		this.usr = userName;
		this.psword = pwd;
		this.cmd = cmd;
		this.markString = markString;
		log.info("AsyncSSHConnection initialized successfully.");
	}
	public Object call() throws Exception { 
		this.exec(this.cmd);
		return "";
	}
	private void exec(String cmd) throws Exception
	{
		log.info("Start AsyncSSHConnection exec, the cmd is - " + cmd);
		Session session = null;
		ChannelExec channel = null;

		try {
			JSch jsch = new JSch();
			session = jsch.getSession(usr, HostName, 22);
			session.setPassword(psword);
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();

			channel = (ChannelExec) session.openChannel("exec");
			BufferedReader brs = new BufferedReader(new InputStreamReader(channel.getInputStream()));
			channel.setCommand(cmd);
			channel.connect();
			
			
			String linetemp = null;
			//Clean the cmdFeedback.value and cmdFeedback.completed
			cmdFeedback.value = "";
			cmdFeedback.completed = false;
			
			//get the thumb print of host
			while((linetemp =brs.readLine()) != null){ 
//			    	cmdFeedback.value = cmdFeedback.value + linetemp;
//			    	log.info("SSH stand output message: " + linetemp);
			    	if(linetemp.contains(this.markString)){
			    		cmdFeedback.value = cmdFeedback.value + linetemp;
			    		log.info("Get the " + this.markString + " successfully!");
			    		cmdFeedback.completed = true;
			    		break;
			    	}
			}	
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new Exception(e);
		}  
		finally
		{
			if (channel != null) {
				channel.disconnect();
			}
			if (session != null) {
				session.disconnect();
			}
		}

	}
}
