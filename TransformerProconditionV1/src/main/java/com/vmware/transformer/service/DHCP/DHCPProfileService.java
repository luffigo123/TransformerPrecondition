package com.vmware.transformer.service.DHCP;
import java.util.ArrayList;

import com.vmware.transformer.model.DHCP.DHCPProfile;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.service.Fabric.EdgeClusterService;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.GetInputString;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class DHCPProfileService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	public String display_name = "";
	public EdgeClusterService edgeClusterService = null;
	
	public String second_displayName = "";
			
	public DHCPProfileService() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/dhcp/server-profiles/";
		
//		display_name = "DHCPProfile001" + TestData.NativeString;
//		second_displayName = "DHCPProfile002" + TestData.NativeString;
		
		display_name = GetInputString.getInputString();
		second_displayName = "DHCPProfile002" + GetInputString.getInputString();
		
		edgeClusterService = new EdgeClusterService();
	}

	
	/**
	 * get specific objectId
	 * @param displayName
	 * @return
	 */
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
	 * Add a DHCPProfile
	 * @param dhcpProfile
	 */
	public void addDHCPProfile(DHCPProfile dhcpProfile){
		service.addObject(dhcpProfile, url);
	}
	
	
	/**
	 * modify the specific DHCPProfile
	 * @param orgDHCPProfileName
	 * @param newDHCPProfile
	 */
	public void modifyDHCPProfile(String orgDHCPProfileName, DHCPProfile newDHCPProfile){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgDHCPProfileName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		//Set new DHCPProfile instance
		DHCPProfile dhcpProfile = newDHCPProfile;
		dhcpProfile.set_revision(_revision);
		
		//generate the edit url
		String tzid = this.getObjectId(orgDHCPProfileName);
		String modifyUrl = url + tzid;
		service.modifyObject(dhcpProfile, modifyUrl);
	}
	
	/**
	 * delete specific DHCPProfile
	 * @param displayName
	 */
	public void deleteDHCPProfile(String displayName){
		String tzid = this.getObjectId(displayName);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	/**
	 * query the specific DHCPProfile info
	 * @param displayName
	 * @return
	 */
	public String queryDHCPProfile(String displayName){
		String tzid = this.getObjectId(displayName);
		String queryUrl = url + tzid;
		return service.queryObject(queryUrl);
	}
	
	/**
	 * Get default DHCPProfile instance
	 * @return
	 */
	public DHCPProfile getDefaultDHCPProfile(){
		this.setupPrecondition();
		String edgeCLusterName = this.edgeClusterService.display_name;
		String edgeClusterId = this.edgeClusterService.getObjectId(edgeCLusterName);
	
		ArrayList<String> edge_cluster_member_indexes = new ArrayList<String>();
		edge_cluster_member_indexes.add("0");
		
		DHCPProfile dhcpProfile = new DHCPProfile(this.display_name, this.display_name, edgeClusterId, edge_cluster_member_indexes);
		
		return dhcpProfile;
	}
	
	public DHCPProfile getSecondDHCPProfile(){
		String edgeCLusterName = this.edgeClusterService.display_name;
		String edgeClusterId = this.edgeClusterService.getObjectId(edgeCLusterName);
	
		ArrayList<String> edge_cluster_member_indexes = new ArrayList<String>();
		edge_cluster_member_indexes.add("0");
		
		DHCPProfile dhcpProfile = new DHCPProfile(this.second_displayName, this.second_displayName, edgeClusterId, edge_cluster_member_indexes);
		return dhcpProfile;
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
	
	public void setupPrecondition(){
		this.edgeClusterService.setupDefaultEdgeCluster();
	}
	
	public void cleanupPrecondition(){
		this.edgeClusterService.cleanup_DefaultEdgeCluster();
	}
	
	
	public void setup_Default_DHCPProfile(){
		if(!this.isExist(this.display_name)){
			this.addDHCPProfile(this.getDefaultDHCPProfile());
		}
		if(!this.isExist(display_name)){
			assert false: "Failed to add DHCPProfile";
		}
		
	}
	
	public void clean_Default_DHCPProfile(){
		if(this.isExist(this.display_name)){
			this.deleteDHCPProfile(this.display_name);
		}
		if(this.isExist(display_name)){
			assert false: "Failed to delete DHCPProfile";
		}
		this.cleanupPrecondition();
	}
	
	public void setup_Second_DHCPProfile(){
		if(!this.isExist(this.second_displayName)){
			this.addDHCPProfile(this.getSecondDHCPProfile());
		}
	}
	
	public void clean_Second_DHCPProfile(){
		if(this.isExist(this.second_displayName)){
			this.deleteDHCPProfile(this.second_displayName);
		}
	}
}
