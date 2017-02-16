package com.vmware.transformer.utils;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;

public class SSHUtils {
	private Logger log = Log4jInstance.getLoggerInstance();
	
	/**
	 * 
	 * @param cmd				The command which you input in the cmd
	 * @param hostIPAddress		The host IPAddress
	 * @param userName			The host's userName
	 * @param password			The host's password
	 * @param markString		 e.g. "SHA256 Fingerprint=6A:42:BB:F1:6B:02:97:1D:95:08:7D:E7:BD:47:AC:13:79:3B:33:0C:3D:94:5D:12:76:22:B5:7C:65:86:3D:5F"
	 * 						    The target string is "6A:42:BB:F1:6B:02:97:1D:95:08:7D:E7:BD:47:AC:13:79:3B:33:0C:3D:94:5D:12:76:22:B5:7C:65:86:3D:5F",
	 *  						and the	"SHA256 Fingerprint" is markString
	 * @return
	 */
	public String getHostThumbprint(String cmd, String hostName,String userName, String password, String markString){
		ThreadSharedData cmdFeedback = ThreadSharedData.getInstance();
		String resultString = "";
		
		Callable<Object> AsyncsshConn = new AsyncSSHConnectionWithJsch(hostName, userName, password, cmd, markString);
		ExecutorService pool = Executors.newFixedThreadPool(1);
		Future<Object> f = pool.submit(AsyncsshConn);
		
		try {
			 f.get(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {

			e.printStackTrace();
		} catch (TimeoutException e) {

			e.printStackTrace();
		}
		
		
		String temp = cmdFeedback.value;
		
		if(temp.contains(markString)){
			int index = markString.length() + 1;
			resultString = temp.substring(index).trim();
			
			log.info("Get the result string successfully, and it's :" + resultString);
		}else{
			log.error("Failed to get result string, please ensure the command is correct.");
		}	
		
		pool.shutdown();
		return resultString;
		
	}
	
	
	public String getManagerThumbprint(String hostName, String userName, String password){
		String thumbprint = "";
		String cmd = "get certificate api thumbprint";
		String markString = "";
		
		ThreadSharedData cmdFeedback = ThreadSharedData.getInstance();

		Callable<Object> AsyncsshConn = new AsyncSSHConnectionWithJsch(hostName, userName, password, cmd, markString);
		ExecutorService pool = Executors.newFixedThreadPool(1);
		Future<Object> f = pool.submit(AsyncsshConn);
		
		try {
			 f.get(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {

			e.printStackTrace();
		} catch (TimeoutException e) {

			e.printStackTrace();
		}
		
		String temp = cmdFeedback.value;
		
		if(temp != null){
			thumbprint = temp;
			log.info("Get the result string successfully, and it's :" + thumbprint);

		}else{
			log.error("Failed to get result string, please ensure the command is correct.");
		}	
		
		pool.shutdown();

		return thumbprint;
	}
	

	/**
	 * 
	 * @param managerIP
	 * @param managerUsername
	 * @param managerPassword
	 * @param hostIP
	 * @param hostUserName
	 * @param hostPassword
	 * @return
	 */
	public boolean registerToManager(String managerIP, String managerUsername, String managerPassword, String hostIP, String hostUserName, String hostPassword){
		boolean result = false;
		String markString = "";
//		String registerSuccessfullyString = "Node successfully registered";
		String registerSuccessfullyString = "successfully";
		String managerThumbprint = this.getManagerThumbprint(managerIP, managerUsername, managerPassword);
		
		String cmd = "join management-plane " + managerIP + " thumbprint " + managerThumbprint.trim() + " username " + managerUsername + " password " + managerPassword;
		
		ThreadSharedData cmdFeedback = ThreadSharedData.getInstance();

		Callable<Object> AsyncsshConn = new AsyncSSHConnectionWithJsch(hostIP, hostUserName, hostPassword, cmd, markString);
		ExecutorService pool = Executors.newFixedThreadPool(1);
		Future<Object> f = pool.submit(AsyncsshConn);
		
		try {
			 f.get(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {

			e.printStackTrace();
		} catch (TimeoutException e) {

			e.printStackTrace();
		}
		
		String temp = cmdFeedback.value;
		
//		if(temp.equalsIgnoreCase(registerSuccessfullyString)){
		if(temp.contains(registerSuccessfullyString)){
			result = true;
			log.info("Registed to Manager successfully!");

		}else{
			log.error("Failed Registed to Manager, please ensure the command is correct.");
		}	
		
		pool.shutdown();

		return result;
	}
	
	/**
	 * 
	 * @param managerIP
	 * @param managerUsername
	 * @param managerPassword
	 * @param hostIP
	 * @param hostUserName
	 * @param hostPassword
	 * @return
	 */
	public boolean registEdgeToManger(String managerIP, String managerUsername, String managerPassword, String hostIP, String hostUserName, String hostPassword){
		return this.registerToManager(managerIP, managerUsername, managerPassword, hostIP, hostUserName, hostPassword);
	}
	
	/**
	 * Connect to the ESXi ssh, and register the esxi to Manager
	 * 
	 * e.g step.1: "/opt/vmware/nsx-cli/bin/scripts/nsxcli"
	 * 	   step 2: join management-plane 10.117.169.37 thumbprint 7e00e96bdc77303e3475b5c2cc4db7a37ae41dab027dd92d49b3770cfd6c41a6 username admin password !QAZ2wsx
	 * 
	 * @param managerIP
	 * @param managerUsername
	 * @param managerPassword
	 * @param hostIP
	 * @param hostUserName
	 * @param hostPassword
	 * @return
	 */
	public void registerESXiHostToManager(String managerIP, String managerUsername, String managerPassword, String hostIP, String hostUserName, String hostPassword){

		String managerThumbprint = this.getManagerThumbprint(managerIP, managerUsername, managerPassword);
		
		String cmd1 = "/opt/vmware/nsx-cli/bin/scripts/nsxcli";
		String cmd2 = "join management-plane " + managerIP + " thumbprint " + managerThumbprint.trim() + " username " + managerUsername + " password " + managerPassword + "\n";
		
		ArrayList<String> commandList = new ArrayList<String>();
		commandList.add(cmd1);
		commandList.add(cmd2);
		
		JschSSHConnection jsc = new JschSSHConnection(hostIP, hostUserName, hostPassword, commandList, "Node successfully registered");
		jsc.exec();
		System.out.print(Thread.currentThread());
	}
	
	
	
}
