package com.vmware.transformer.service.Fabric;

import java.util.ArrayList;

import com.vmware.transformer.model.Fabric.EdgeNode;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.GetInputString;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SSHUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class EdgeNodeService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	public String edgeNodeIP = "";
	public String edgeUserName = "";
	public String edgePasswd = "";
	
	
	//set default Logical Switch dsplay_name
//	public String display_name = "EdgeNode001" + TestData.NativeString;
	public String display_name;
	
	public EdgeNodeService() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/fabric/nodes/";
		
		edgeNodeIP = DefaultEnvironment.edgeNodeIP;
		edgeUserName = DefaultEnvironment.edgeUserName;
		edgePasswd = DefaultEnvironment.edgePasswd;
		
		display_name = GetInputString.getInputString();
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
	
	public void addEdgeNode(EdgeNode edgeNode){
		service.addObject(edgeNode, url);
	}
	

	public void modifyEdgeNode(String orgEdgeNodeName, EdgeNode newEdgeNode){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgEdgeNodeName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		EdgeNode finalObject = newEdgeNode;
		finalObject.set_revision(_revision);
		
		//generate the edit url
		String tzid = this.getObjectId(orgEdgeNodeName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);

	}
	
	
	public void deleteEdgeNode(String displayName){
		String tzid = this.getObjectId(displayName);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}

	
	public String queryEdgeNodeInfo(String displayName){
		String tzid = this.getObjectId(displayName);
		String queryUrl = url + tzid;
		return service.queryObject(queryUrl);
	}
	
	public String queryAllNodesInfo(){
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
	
	public String getEdgeNodeUUid(String hostName,String userName, String password){
		String uuid = "";
		String cmd = "get node-uuid";
		String markString = "uuid:";
		SSHUtils sshUtils = new SSHUtils();
		uuid = sshUtils.getHostThumbprint(cmd, hostName, userName, password, markString);
		return uuid;
		
	}
	
	public EdgeNode getDefaultEdgeNode(){
		ArrayList<String> edgeIP_list = new ArrayList<String>();
		edgeIP_list.add(edgeNodeIP);
		String edgeNode_uuid = this.getEdgeNodeUUid(edgeNodeIP, edgeUserName, edgePasswd);
		
		EdgeNode defaultEdgeNode = new EdgeNode(display_name, display_name, edgeIP_list, edgeNode_uuid, "EdgeNode");
		
		return defaultEdgeNode;
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
	public boolean registerEdgeToManager(String managerIP,String managerUsername,String managerPassword,String hostIP,String hostUserName,String hostPassword){
		boolean result = false;
		
		SSHUtils su = new SSHUtils();
		result = su.registEdgeToManger(managerIP, managerUsername, managerPassword, hostIP, hostUserName, hostPassword);
		
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
	 * Get edgeName by Edge IPaddress
	 * @param edge_ipAddress
	 * @return
	 */
	public String getEdgeName_ByEdgeIPAddress(String edge_ipAddress){
		String edgeName = "";
		JsonUtils ju = new JsonUtils();
		String jsonString = this.queryAllNodesInfo();
		String promptPropertyName = "ip_addresses";
		String targetPropertyName = "display_name";
		
		edgeName = ju.getPropertyValue_InSpecificArrayList(jsonString, promptPropertyName, edge_ipAddress, targetPropertyName);
		return edgeName;
	}
	
	public void setup_defaultEdgeNode(){
		if(!this.isExist(this.display_name)){
			this.addEdgeNode(this.getDefaultEdgeNode());
		}
	}
	
	public void cleanup_defaultEdgeNode(){
		if(this.isExist(this.display_name)){
			this.deleteEdgeNode(this.display_name);
		}
	}
	
	public void setup_EdgeNode_ByRegisterCommand(){
		String edgeName = this.getEdgeName_ByEdgeIPAddress(this.edgeNodeIP);
		String managerUsername = DefaultEnvironment.VSMUserName;
		String managerPassword = DefaultEnvironment.VSMPassword;
		if(!this.isExist(edgeName)){
			this.registerEdgeToManager(this.vmsIP, managerUsername, managerPassword, this.edgeNodeIP, this.edgeUserName, this.edgePasswd);
		}
	}
	
	public void cleanup_EdgeNode_ByRegisterCommand(){
		String edgeName = this.getEdgeName_ByEdgeIPAddress(this.edgeNodeIP);
		if(this.isExist(edgeName)){
			this.deleteEdgeNode(edgeName);
		}
	}
	
}
