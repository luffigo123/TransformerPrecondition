package com.vmware.transformer.service.Routing;

import java.util.ArrayList;

import com.vmware.transformer.model.Routing.VRF;
import com.vmware.transformer.model.Routing.VRF.AddressFamily;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.TestData;
import com.vmware.transformer.utils.VerifyUtils;

public class VRFService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;

	public RoutingService routingService = null;
	public String logicalRouterId = "";
	public String logiclaRouter_DisplayName = "";

	public String dislayName = "";


	public VRFService() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		routingService = new RoutingService();
		url = "https://" + vmsIP + "/api/v1/vrfs/";
		dislayName = "VRF001" + TestData.NativeString;
	}
	
	/**
	 * 
	 * @param display_name
	 * @return
	 */
	public String getObjectId(String display_name){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = display_name;
		String targetPropertyName = "id";
		String tzid = jsonUtils.getPropertyValue(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName);
		return tzid;
	}
	
	public void addVRF(VRF vrf){
		service.addObject(vrf, url);
	}
	
	/**
	 * 
	 * @param orgVRF_displayName
	 * @param newVRF
	 */
	public void modifyVRF(String orgVRF_displayName, VRF newVRF){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgVRF_displayName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		newVRF.set_revision(_revision);
		VRF finalObject = newVRF;
		
		//generate the edit url
		String tzid = this.getObjectId(orgVRF_displayName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);
	}
	
	/**
	 * 
	 * @param display_name
	 */
	public void deleteVRF(String display_name){
		String id = this.getObjectId(display_name);
		String deleteUrl = url + id;
		service.deleteObject(deleteUrl);
	}
	
	/**
	 * 
	 * @param display_name
	 * @return
	 */
	public String querySpecificVRF(String display_name){
		String id = this.getObjectId(display_name);
		String queryUrl = url + id;
		return service.queryObject(queryUrl);
	}
	
	public String queryAllInfo(){
		return service.queryObject(url);
	}
	
	/**
	 * check the specific object whether exist by displayName
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
	
	public VRF getDefaultVRF(){
		String tier = "Tier0";
		this.setPrecondition(tier);

		String logicalRouterId = this.routingService.getObjectId(this.routingService.display_name_tier0);
		String route_distinguisher = "1.1.1.1";
		
		ArrayList<String> import_route_targets_list = new ArrayList<String>();
		String import_route_targets_001 = "2.2.2.2";
		import_route_targets_list.add(import_route_targets_001);
		
		ArrayList<String> export_route_targets_list = new ArrayList<String>();
		String export_route_targets_001 = "3.3.3.3";
		export_route_targets_list.add(export_route_targets_001);
		
		ArrayList<AddressFamily> address_families = new ArrayList<AddressFamily>();
		String maximum_routes = "3";
		AddressFamily addressFamily01 = new AddressFamily(import_route_targets_list, export_route_targets_list, maximum_routes);
		address_families.add(addressFamily01);

		VRF vrf =  new VRF(this.dislayName, route_distinguisher, logicalRouterId, address_families);
		return vrf;
	}
	
	public void setPrecondition(String tier){
		this.routingService.setupDefault_LogicalRouter(tier);
	}
	
	public void cleanupPrecondition(String tier){
		this.routingService.clean_Default_LogicalRouter(tier);
		this.routingService.cleanPrecondition();
	}
	
	public void setup_defaultVRF(){
		if(!this.isExist(this.dislayName)){
			this.addVRF(this.getDefaultVRF());
		}
	}
	
	public void cleanup_defaultVRF(){
		if(this.isExist(this.dislayName)){
			this.deleteVRF(this.dislayName);
		}
	}
	
}
