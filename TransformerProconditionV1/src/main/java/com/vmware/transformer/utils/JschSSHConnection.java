package com.vmware.transformer.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class JschSSHConnection{
	private String HostName = null;
	private String username = null;
	private String password = null;
	private ArrayList<String> cmdList = null;
	private Session session = null;

	private Logger log = Log4jInstance.getLoggerInstance();
	
	private ThreadSharedData cmdFeedback = ThreadSharedData.getInstance();
	private String markString = "";
	
	/**
	 * 
	 * @param hostName
	 * @param userName
	 * @param pwd
	 * @param cmd
	 * @param markString
	 */
	public JschSSHConnection(String hostName, String userName, String pwd, ArrayList<String> cmdList, String markString)
	{
		this.HostName = hostName;
		this.username = userName;
		this.password = pwd;
		this.cmdList = cmdList;
		this.markString = markString;
		log.info("JschSSHConnection initialized successfully.");
	}
	

	public void exec()
	{
		this.initSession(username, password, HostName);
		this.runCommand();
		this.disconnectSession();
	}
	
	private void initSession(String username, String password,String host) {
		JSch jsch = new JSch();
		try {
			session = jsch.getSession(username, host, 22);
			session.setPassword(password);
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void runCommand(){
		Channel channel = null;
		InputStream is = null;
		BufferedReader buffReader = null;
		try {	
			for(int i = 0 ; i< cmdList.size(); i++){
				String command = cmdList.get(i);
				if(i == 0){
					channel = (ChannelExec) session.openChannel("exec");
					((ChannelExec) channel).setCommand(command);	
				}else{
					is  = new ByteArrayInputStream(command.getBytes(StandardCharsets.UTF_8));
				}
			}
		
		    channel.setInputStream(is);
			channel.setOutputStream(System.out);
			((ChannelExec)channel).setErrStream(System.err);
			
			InputStream getinputStream = channel.getInputStream();
			InputStreamReader inputReader = new InputStreamReader(getinputStream,"utf-8");
			buffReader = new BufferedReader(inputReader);
	
		    channel.connect();
		    
		    try{Thread.sleep(5000);}catch(Exception ee){}
		    

			String linetemp = null;
			//Clean the cmdFeedback.value and cmdFeedback.completed
			cmdFeedback.value = "";
			cmdFeedback.completed = false;
			
			//get the thumb print of host
			while((linetemp =buffReader.readLine()) != null){ 
			    	if(linetemp.contains(this.markString)){
			    		cmdFeedback.value = cmdFeedback.value + linetemp;
			    		log.info("ESXi host registered to manager successfully!");
			    		cmdFeedback.completed = true;
			    		break;
			    	}
			}	
		    
			}catch (Exception e) {
				e.printStackTrace();
			}

	    channel.disconnect();
	}
	
	private void disconnectSession(){
		session.disconnect();
    }
}
