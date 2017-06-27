package com.vmware.transformer.service.Fabric;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.vmware.transformer.model.Fabric.HostNode;
import com.vmware.transformer.model.Fabric.HostNode.Host_Credential;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.Log4jInstance;
import com.vmware.transformer.utils.SSHUtils;
import com.vmware.transformer.utils.SpringUtils;

public class HostNodeServicePreCon {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	public String host001_IPAddress = "";
	public String hostUserName = "";
	public String hostPassword = "";
	
	public String managerIP = "";
	public String managerUserName ="";
	public String managerPasswd = "";
	
	//ESXI,RHELKVM,UBUNTUKVM
	public String os_type = "ESXI";
	//HostNode,EdgeNode
	public String resource_type = "HostNode";
	
	Logger log = Log4jInstance.getLoggerInstance();
	
	public HostNodeServicePreCon() {
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
		
	}
	
	public HostNode getDefaultHostNode(String esxiNodeName){

		ArrayList<String> list = new ArrayList<String>();
		list.add(host001_IPAddress);
		
		String hostNode_Thumbprint = this.getHostNodeThumbprint(host001_IPAddress, hostUserName, hostPassword);
		HostNode.Host_Credential hostCredential = new Host_Credential(hostUserName, hostPassword, hostNode_Thumbprint);
		
		HostNode hostNode = new HostNode(esxiNodeName, list, os_type, resource_type, hostCredential);
		
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
	

	public void addNode(HostNode hostNode){
		service.addObject(hostNode, url);
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
	

}
