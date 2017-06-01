package com.vmware.transformer.service.Fabric;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.vmware.transformer.model.Fabric.HostNode;
import com.vmware.transformer.model.Fabric.HostNode.Host_Credential;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.GetInputString;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.Log4jInstance;
import com.vmware.transformer.utils.SSHUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class HostNodeService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	public String host001_IPAddress = "";
	public String hostUserName = "";
	public String hostPassword = "";
	
	public String managerIP = "";
	public String managerUserName ="";
	public String managerPasswd = "";
	
	//set default Logical Switch dsplay_name
//	public String display_name = "HostNode" + TestData.NativeString;
	public String display_name;

	//ESXI,RHELKVM,UBUNTUKVM
	public String os_type = "ESXI";
	//HostNode,EdgeNode
	public String resource_type = "HostNode";
	
	Logger log = Log4jInstance.getLoggerInstance();
	
	public HostNodeService() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/fabric/nodes/";
		
		managerIP = DefaultEnvironment.VSMIP;
		managerUserName = DefaultEnvironment.VSMUserName;
		managerPasswd = DefaultEnvironment.VSMPassword;
		
		host001_IPAddress = DefaultEnvironment.host001_IPAddress;
		hostUserName = DefaultEnvironment.hostUserName;
		hostPassword = DefaultEnvironment.hostPassword;
		
		display_name = GetInputString.getInputString();
	}
	
	public HostNode getDefaultHostNode(){

		ArrayList<String> list = new ArrayList<String>();
		list.add(host001_IPAddress);
		
		String hostNode_Thumbprint = this.getHostNodeThumbprint(host001_IPAddress, hostUserName, hostPassword);
		HostNode.Host_Credential hostCredential = new Host_Credential(hostUserName, hostPassword, hostNode_Thumbprint);
		
		HostNode hostNode = new HostNode(display_name, list, os_type, resource_type, hostCredential);
		
		return hostNode;
	}
	
	public String getObjectId(String displayName){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = displayName;
		String targetPropertyName = "id";
		String tzid = jsonUtils.getPropertyValue(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName);
		return tzid;
	}
	

	public String getObjectIdBySpecificItem(String itemName, String itemValue){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = itemName;
		String promptPropertyValue = itemValue;
		String targetPropertyName = "id";
		String tzid = jsonUtils.getPropertyValue_ContainSpecificString(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName);
		return tzid;
	}
	
	public String getDefaultHostNodeId(){
		String hostNodeId = "";
		HostNode hostNode = this.getDefaultHostNode();
		String defaultHostNodeName = hostNode.getDisplay_name();
		if(!this.isExist(defaultHostNodeName)){
			this.addNode(hostNode);
		}
		hostNodeId  = this.getObjectId(defaultHostNodeName);
		return hostNodeId;
	}
	
	
	public void addNode(HostNode hostNode){
		service.addObject(hostNode, url);
	}
	

	
	public void deleteNode(String displayName){
		String tzid = this.getObjectId(displayName);
		String deleteUrl = url + tzid + "/?unprepare_host=false";
		service.deleteObject(deleteUrl);
	}

	
	public String queryNodeInfo(String displayName){
		String tzid = this.getObjectId(displayName);
		String queryUrl = url + tzid;
		return service.queryObject(queryUrl);
	}
	
	public String queryAllNodeInfo(){
		String queryUrl = url;
		return service.queryObject(queryUrl);
	}
	
	
	/**
	 * check the specific object whether exist by name
	 * @param displayName
	 * @return
	 */
	public boolean isExist(String displayName){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		VerifyUtils verifyUtils = new VerifyUtils();
		String jsonString = service.queryObject(url);
		String targetPropertyName = "display_name";
		boolean b = verifyUtils.isTargetExist(jsonString, targetPropertyName, displayName);
		return b;
	}
	
	/**
	 * Get the specific ESXi host thumbprint
	 * @param hostIPAddress
	 * @param userName
	 * @param password
	 * @return
	 */
	public String getHostNodeThumbprint(String hostIPAddress, String userName, String password){
		String result = "";
		String cmd = "openssl x509 -in /etc/vmware/ssl/rui.crt -fingerprint -sha256 -noout";
		String markString = "SHA256 Fingerprint";
		SSHUtils sshUtils = new SSHUtils();
		result = sshUtils.getHostThumbprint(cmd, hostIPAddress, userName, password, markString);
		return result;
	}
	
	/**
	 * check the node status whether is up during the time
	 * @param minutes
	 * @param nodeId
	 * @param key			mpa_connectivity_status | lcp_connectivity_status
	 * @param valueType		int | string | boolean
	 * @return
	 */
	public boolean isNodeReady(int minutes, String nodeId, String key, String valueType){
		boolean result = false;
		JsonUtils util = new JsonUtils();
		String url = this.url + nodeId + "/status";
		String queryInfo = "";
		int begin = 0;
		while(begin <= (minutes * 60)){
			queryInfo = service.queryObject(url);
			String mpa = util.getJsonItemValue(queryInfo, key, valueType);
			if("up".equalsIgnoreCase(mpa)){
				result = true;
				break;
			}else{
				try {
					Thread.sleep(20000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				begin = begin + 20;
			}
		}
		return result;
	}
	
	/**
	 * check the node status whether installed successfully during the time
	 * @param minutes
	 * @param nodeId
	 * @param key
	 * @param valueType				string | int | boolean
	 * @param checkItemStatusValue  INSTALL_SUCCESSFUL
	 * @return
	 */
	public boolean isNodeReady(int minutes, String nodeId, String key, String valueType, String checkItemStatusValue){
		boolean result = false;
		JsonUtils util = new JsonUtils();
		String url = this.url + nodeId + "/status";
		String queryInfo = "";
		int begin = 0;
		while(begin <= (minutes * 60)){
			queryInfo = service.queryObject(url);
			String keyValue = util.getJsonItemValue(queryInfo, key, valueType);
			if(checkItemStatusValue.equalsIgnoreCase(keyValue)){
				result = true;
				break;
			}else{
				try {
					Thread.sleep(20000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				begin = begin + 20;
			}
		}
		return result;
	}
	
	/**
	 * 	 Register host to manager by esxi shell command
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
	public void registerESXiHostToManager(String managerIP,String managerUsername,String managerPassword,String hostIP,String hostUserName,String hostPassword){
		SSHUtils su = new SSHUtils();
		su.registerESXiHostToManager(managerIP, managerUsername, managerPassword, hostIP, hostUserName, hostPassword);

	}
	
	/**
	 * Get node display name bys IPaddress
	 * @param ipAddress
	 * @return
	 */
	public String getNodeName_ByIPAddress(String ipAddress){
		String edgeName = "";
		JsonUtils ju = new JsonUtils();
		String jsonString = this.queryAllNodeInfo();
		String promptPropertyName = "ip_addresses";
		String targetPropertyName = "display_name";
		
		edgeName = ju.getPropertyValue_InSpecificArrayList(jsonString, promptPropertyName, ipAddress, targetPropertyName);
		return edgeName;
	}
	
	public void setup_defaultHostNode(){
		if(!this.isExist(this.display_name)){
			this.addNode(this.getDefaultHostNode());
		}
		boolean status = this.isNodeReady(15, this.getObjectId(display_name), "host_node_deployment_status", "string","INSTALL_SUCCESSFUL");
		if(!status){
			log.error("The host status is not UP, please check up the host whether is ready");
		}else{
			log.info("The host Node is ready!");
		}
	}
	
	public void cleanup_defaultHostNode(){
		if(this.isExist(this.display_name)){
			this.deleteNode(this.display_name);
		}
	}
	
	public void setup_defaultESXiHostByCommand(){
		String defaultHostName = this.getNodeName_ByIPAddress(this.host001_IPAddress);
		if(!this.isExist(defaultHostName)){
			this.registerESXiHostToManager(this.managerIP, this.managerUserName, this.managerPasswd, 
					this.host001_IPAddress, this.hostUserName, this.hostPassword);
		}
		
		boolean status = this.isNodeReady(15, this.getObjectId(defaultHostName), "host_node_deployment_status", "string","INSTALL_SUCCESSFUL");
		if(!status){
			log.error("The host status is not UP, please check up the host whether is ready");
		}else{
			log.info("The host Node is ready!");
		}
		
	}
	
	public void cleanup_defaultESXiHostByCommand(){
		String defaultHostName = this.getNodeName_ByIPAddress(this.host001_IPAddress);
		if(this.isExist(defaultHostName)){
			this.deleteNode(defaultHostName);
		}
	}

}
