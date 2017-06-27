package com.vmware.transformer.service.Fabric;

import com.vmware.transformer.model.Fabric.EdgeNode;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SSHUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class EdgeNodeServicePreCon {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
//	public String edgeNodeIP = "";
	public String edgeUserName = "";
	public String edgePasswd = "";
	
	
//	public String display_name;
	
	public EdgeNodeServicePreCon() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/fabric/nodes/";
		
//		edgeNodeIP = DefaultEnvironment.edgeNodeIP;
		edgeUserName = DefaultEnvironment.edgeUserName;
		edgePasswd = DefaultEnvironment.edgePasswd;
		
//		display_name = GetInputString.getInputString();
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

	public void setup_EdgeNode_ByRegisterCommand(String edgeNodeIP){
//		String edgeName = this.getEdgeName_ByEdgeIPAddress(this.edgeNodeIP);
		String edgeName = this.getEdgeName_ByEdgeIPAddress(edgeNodeIP);
		String managerUsername = DefaultEnvironment.VSMUserName;
		String managerPassword = DefaultEnvironment.VSMPassword;
		if(!this.isExist(edgeName)){
			this.registerEdgeToManager(this.vmsIP, managerUsername, managerPassword, edgeNodeIP, this.edgeUserName, this.edgePasswd);
		}
	}
	

}
