package com.vmware.transformer.service.Fabric;

import com.vmware.transformer.model.Fabric.EdgeClusterProfile;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class EdgeClusterProfileServicePreCon {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	

//	public String display_name;
//	public String display_name_02;

	public EdgeClusterProfileServicePreCon() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/cluster-profiles/";
		
//		display_name = GetInputString.getInputString();
//		display_name_02 = display_name + "002";
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
	

	public EdgeClusterProfile getDefaultEdgeClusterProfile(String edgeClusterProfileName){
		String resource_type = "EdgeHighAvailabilityProfile";
		EdgeClusterProfile defaultEdgeClusterProfile = new EdgeClusterProfile(edgeClusterProfileName, edgeClusterProfileName, "1000", "255", "3", resource_type);
		return defaultEdgeClusterProfile;
	}
	


}
