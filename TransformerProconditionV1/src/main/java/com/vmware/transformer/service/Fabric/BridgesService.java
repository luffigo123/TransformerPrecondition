package com.vmware.transformer.service.Fabric;

import java.util.ArrayList;

import com.vmware.transformer.model.Fabric.BridgeCluster;
import com.vmware.transformer.model.Fabric.BridgeNode;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.GetInputString;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class BridgesService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	public TransportNodeService transportNodeService;
	
	//set default Logical Switch dsplay_name
//	public String display_name = "BridgeCluster" + TestData.NativeString;
	
	public String display_name;
	
	public BridgesService() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/bridge-clusters/";
		
		transportNodeService = new TransportNodeService();
		
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
	
	/**
	 * 
	 * @param bridgeCluster
	 */
	public void addBridgeCluster(BridgeCluster bridgeCluster){
		service.addObject(bridgeCluster, url);
	}
	
	/**
	 * 
	 * @param orgBridgeClusterName
	 * @param newBridgeCluster
	 */
	public void modifyBridgeCluster(String orgBridgeClusterName, BridgeCluster newBridgeCluster){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgBridgeClusterName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		BridgeCluster finalObject = newBridgeCluster;
		finalObject.set_revision(_revision);
		
		//generate the edit url
		String tzid = this.getObjectId(orgBridgeClusterName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);

	}
	
	/**
	 * 
	 * @param displayName
	 */
	public void deleteBridgeCluster(String displayName){
		String id = this.getObjectId(displayName);
		String deleteUrl = url + id;
		service.deleteObject(deleteUrl);
	}
	
	/**
	 * 
	 * @param displayName
	 * @return
	 */
	public String queryBridgeCluster(String displayName){
		String id = this.getObjectId(displayName);
		String queryUrl = url + id;
		return service.queryObject(queryUrl);
	}
	
	/**
	 * check the specific object whether exist by name
	 * @param displayName
	 * @return
	 */
	public boolean isExist(String displayName){
		VerifyUtils verifyUtils = new VerifyUtils();
		String jsonString = service.queryObject(url);
		String targetPropertyName = "display_name";
		boolean b = verifyUtils.isTargetExist(jsonString, targetPropertyName, displayName);
		return b;
	}
	
	public BridgeCluster getDefaultBridgeCluster(){ 
		this.setupPrecondition();
		String transNodeId = this.transportNodeService.getObjectId(this.transportNodeService.transNode_esxihost_diaplayName);
		
		BridgeNode bridgeNode = new BridgeNode(transNodeId);
		ArrayList<BridgeNode> bridge_nodes = new ArrayList<BridgeNode>();
		bridge_nodes.add(bridgeNode);
		
		BridgeCluster bridgeCluster = new BridgeCluster(this.display_name, this.display_name, bridge_nodes);
		
		return bridgeCluster;
	}
	
	public void setupPrecondition(){
		this.transportNodeService.setup_defaultTransNode_withESXiHost();
	}
	
	public void cleanPrecondition(){
		this.transportNodeService.cleanup_defaultTransNode_withESXiHost();
	}
	
	public void setup_defaultBridgeService(){
		if(!this.isExist(this.display_name)){
			this.addBridgeCluster(this.getDefaultBridgeCluster());
		}
		
		if(!this.isExist(display_name)){
			assert false: "Failed to add BridgeCluster!";
		}
	}
	
	public void cleanup_defaultBridgeService(){
		if(this.isExist(this.display_name)){
			this.deleteBridgeCluster(this.display_name);
		}
		
		if(this.isExist(display_name)){
			assert false: "Failed to delete BridgeCluster";
		}
		
		this.cleanPrecondition();
	}
}
