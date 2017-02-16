package com.vmware.transformer.service.Fabric;

import com.vmware.transformer.model.Fabric.EdgeClusterProfile;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.GetInputString;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class EdgeClusterProfileService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	//set default Logical Switch dsplay_name
//	public String display_name = "EdgeClusterProfile" + TestData.NativeString;
//	public String display_name_02 = display_name + "002";
	
	public String display_name;
	public String display_name_02;

	public EdgeClusterProfileService() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/cluster-profiles/";
		
		display_name = GetInputString.getInputString();
		display_name_02 = display_name + "002";
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
	 * @param edgeClusterProfile
	 */
	public void addEdgeCusterProfile(EdgeClusterProfile edgeClusterProfile){
		service.addObject(edgeClusterProfile, url);
	}
	
	/**
	 * 
	 * @param orgEdgeClusterProfileName
	 * @param newEdgeClusterProfile
	 */
	public void modifyEdgeClusterProfile(String orgEdgeClusterProfileName, EdgeClusterProfile newEdgeClusterProfile){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgEdgeClusterProfileName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		EdgeClusterProfile finalObject = newEdgeClusterProfile;
		finalObject.set_revision(_revision);
		
		//generate the edit url
		String tzid = this.getObjectId(orgEdgeClusterProfileName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);

	}
	
	/**
	 * 
	 * @param displayName
	 */
	public void deleteEdgeClusterProfile(String displayName){
		String tzid = this.getObjectId(displayName);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	
	public String queryEdgeClusterProfile(String displayName){
		String tzid = this.getObjectId(displayName);
		String queryUrl = url + tzid;
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
	
	public String getDefaultEdgeClusterProfileId(){
		
		EdgeClusterProfile defaultEdgeClusterProfile = this.getDefaultEdgeClusterProfile();
		String defaultName = defaultEdgeClusterProfile.getDisplay_name();
		if(!this.isExist(defaultName)){
			this.addEdgeCusterProfile(defaultEdgeClusterProfile);
		}
		
		String id = "";
		id = this.getObjectId(defaultName);
		return id;
	}
	
	public EdgeClusterProfile getDefaultEdgeClusterProfile(){
		String resource_type = "EdgeHighAvailabilityProfile";
		EdgeClusterProfile defaultEdgeClusterProfile = new EdgeClusterProfile(display_name, display_name, "1000", "2", "2", resource_type);
		return defaultEdgeClusterProfile;
	}
	
	public EdgeClusterProfile getSecondEdgeClusterProfile(){
		String resource_type = "EdgeHighAvailabilityProfile";
		EdgeClusterProfile defaultEdgeClusterProfile = new EdgeClusterProfile(this.display_name_02, this.display_name_02, "1000", "2", "2", resource_type);
		return defaultEdgeClusterProfile;
	}
	
	public void setup_defaultEdgeClusterProfile(){
		if(!this.isExist(this.display_name)){
			this.addEdgeCusterProfile(this.getDefaultEdgeClusterProfile());
		}
		
		if(!this.isExist(display_name)){
			assert false: "Failed to add EdgeClusterProfile!";
		}
	}
	
	public void cleanup_defaultEdgeClusterProfile(){
		if(this.isExist(this.display_name)){
			this.deleteEdgeClusterProfile(this.display_name);
		}
		
		if(this.isExist(display_name)){
			assert false: "Failed to delete EdgeClusterProfile";
		}
	}
	
	public void setup_secondEdgeClusterProfile(){
		if(!this.isExist(this.display_name_02)){
			this.addEdgeCusterProfile(this.getSecondEdgeClusterProfile());
		}
	}
	
	public void cleanup_secondEdgeClusterProfile(){
		if(this.isExist(this.display_name_02)){
			this.deleteEdgeClusterProfile(this.display_name_02);
		}
	}
	
}
