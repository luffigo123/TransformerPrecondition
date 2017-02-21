package com.vmware.transformer.service.Routing;

import java.util.ArrayList;

import com.vmware.transformer.model.Fabric.EdgeCluster;
import com.vmware.transformer.model.Routing.LogicalRouter;
import com.vmware.transformer.model.Routing.LogicalRouterTier0;
import com.vmware.transformer.model.Routing.LogicalRouterTier0.AdvanceConfig;
import com.vmware.transformer.model.Routing.LogicalRouterTier1;
import com.vmware.transformer.model.Routing.LogicalRouterTier1.AdvanceConfige;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.service.Fabric.EdgeClusterService;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.GetInputString;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class RoutingService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	public EdgeClusterService edgeClusterService = null;
	
	public String display_name_tier0 = "";
	public String display_name_tier1 = "";
	
	public String IntraTier0TransitNetwork = "";
	public String IntraTier1TransitNetwork = "";
	public String Tier0Tier1TransitNetwork = "";
	
	public RoutingService() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/logical-routers/";
		
		edgeClusterService = new EdgeClusterService();
		
//		display_name_tier0 = "Tier0_LogicalRouter" + TestData.NativeString;
//		display_name_tier1 = "Tier1_LogicalRouter" + TestData.NativeString;
		
		display_name_tier0 = GetInputString.getInputString();
		display_name_tier1 = GetInputString.getInputString();
		
		IntraTier0TransitNetwork = DefaultEnvironment.IntraTier0TransitNetwork;
		IntraTier1TransitNetwork = DefaultEnvironment.IntraTier1TransitNetwork;
		Tier0Tier1TransitNetwork = DefaultEnvironment.Tier0Tier1TransitNetwork;
		
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
	
	public void addLogicalRouter(LogicalRouter logicalRouter){
		service.addObject(logicalRouter, url);
	}
	
	/**
	 * 
	 * @param orgLogicalRouterName
	 * @param newLogicalRouter
	 */
	public void modifyLogicalRouter(String orgLogicalRouterName, LogicalRouterTier0 newLogicalRouter){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgLogicalRouterName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision_value = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		newLogicalRouter.set_revision(_revision_value);
		LogicalRouter finalObject = newLogicalRouter;
		
		//generate the edit url
		String tzid = this.getObjectId(orgLogicalRouterName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);
	}
	
	/**
	 * 
	 * @param orgLogicalRouterName
	 * @param newLogicalRouter
	 */
	public void modifyLogicalRouter_Tier1(String orgLogicalRouterName, LogicalRouterTier1 newLogicalRouter){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgLogicalRouterName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision_value = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
	
		newLogicalRouter.set_revision(_revision_value);
		LogicalRouter finalObject = newLogicalRouter;
		
		//generate the edit url
		String tzid = this.getObjectId(orgLogicalRouterName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);
	}
	
	/**
	 * 
	 * @param displayName
	 */
	public void deleteLogicalRouter(String displayName){
		String tzid = this.getObjectId(displayName);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	/**
	 * 
	 * @param displayName
	 * @return
	 */
	public String querySpecificLogicalRouter(String displayName){
		String id = this.getObjectId(displayName);
		String queryUrl = url + id;
		return service.queryObject(queryUrl);
	}
	
	public String queryAllLogicalRoutersInfo(){
		return service.queryObject(url);
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
	
	
	public LogicalRouterTier0 getDefault_Tier0_LogicalRouter(){
		this.setPrecondition();
		String edgeClusterId = this.edgeClusterService.getObjectId(this.edgeClusterService.display_name);
		
		String internal_transit_network = this.IntraTier0TransitNetwork;
		ArrayList<String> advanceConfig_list = new ArrayList<String>();
		advanceConfig_list.add(this.Tier0Tier1TransitNetwork);
		AdvanceConfig advance_config = new AdvanceConfig(internal_transit_network, advanceConfig_list);
		
		String router_type = "TIER0";
		String high_availability_mode = "ACTIVE_ACTIVE";
		LogicalRouterTier0 logicalRouter = new LogicalRouterTier0(this.display_name_tier0, this.display_name_tier0, edgeClusterId, router_type, high_availability_mode, advance_config, null);
		
		return logicalRouter;
	}
	
	public LogicalRouterTier0 getDefaultTier0LogicalRouter_withActiveStandbyMode(){
		this.setPrecondition();
		String edgeClusterId = this.edgeClusterService.getObjectId(this.edgeClusterService.display_name);
		
		String internal_transit_network = this.IntraTier0TransitNetwork;
		ArrayList<String> advanceConfig_list = new ArrayList<String>();
		advanceConfig_list.add(this.Tier0Tier1TransitNetwork);
		AdvanceConfig advance_config = new AdvanceConfig(internal_transit_network, advanceConfig_list);
		
		String router_type = "TIER0";
		String high_availability_mode = "ACTIVE_STANDBY";
		LogicalRouterTier0 logicalRouter = new LogicalRouterTier0(this.display_name_tier0, this.display_name_tier0, edgeClusterId, router_type, high_availability_mode, advance_config, null);
		
		return logicalRouter;
	}
	
	public LogicalRouterTier1 getDefault_Tier1_LogicalRouter(){
		this.setPrecondition();
		String edgeClusterId = this.edgeClusterService.getObjectId(this.edgeClusterService.display_name);
		
		String internal_transit_network = this.IntraTier1TransitNetwork;
		AdvanceConfige advanced_config = new AdvanceConfige(internal_transit_network);
		
		String router_type = "TIER1";
		String high_availability_mode = "ACTIVE_STANDBY";
		
		LogicalRouterTier1 logicalRouter_tier1 = new LogicalRouterTier1(this.display_name_tier1, this.display_name_tier1, edgeClusterId, advanced_config, router_type, high_availability_mode);
		return logicalRouter_tier1;
	}

	/**
	 * 
	 * @param tierType	Tier0, Tier1
	 * @return
	 */
	public String get_DisplayName(String tierType){
		String display_name = "";
		if("Tier0".equals(tierType)){
			display_name = this.display_name_tier0;
		}else if("Tier1".equals(tierType)){
			display_name = this.display_name_tier1;
		}
		return display_name;
	}
	
	
	/**
	 * 
	 * @param tierType	Tier0, Tier1
	 * @return
	 */
	public LogicalRouter get_LogicalRouter(String tierType){
		LogicalRouter logicalRouter = null;
		
		if("Tier0".equals(tierType)){
			logicalRouter = this.getDefault_Tier0_LogicalRouter();
		}else if("Tier1".equals(tierType)){
			logicalRouter = this.getDefault_Tier1_LogicalRouter();
		}
		
		return logicalRouter;
	}
	
	
	/**
	 * 1.Create the default EdgeCluster
	 */
	public void setPrecondition(){
		EdgeCluster edgeCluster = this.edgeClusterService.getDefaultEdgeCluster();
		String edgeClusterName = edgeCluster.getDisplay_name();
		if(!this.edgeClusterService.isExist(edgeClusterName)){
			this.edgeClusterService.addEdgeCuster(edgeCluster);
		}
			
	}
	
	/**
	 * clean the pre-condition environment
	 */
	public void cleanPrecondition(){
		
		if(this.edgeClusterService.isExist(this.edgeClusterService.display_name)){
			this.edgeClusterService.deleteEdgeCluster(this.edgeClusterService.display_name);
		}
		this.edgeClusterService.clean_Precondition();
	}
	
	/**
	 * 
	 * @param tierType		Tier0, Tier1
	 */
	public void setupDefault_LogicalRouter(String tierType){
		String display_name = this.get_DisplayName(tierType);
		LogicalRouter logicalRouter = this.get_LogicalRouter(tierType);
		
		if(!this.isExist(display_name)){
			this.addLogicalRouter(logicalRouter);
		}
		
		if(!this.isExist(display_name)){
			assert false: "Failed to add LogicalRouter";
		}
	}
	
	/**
	 * 
	 * @param tierType		Tier0, Tier1
	 */
	public void setupDefaultLogicalRouter_withActiveStandbyMode(String tierType){
		String display_name = this.get_DisplayName(tierType);
		LogicalRouter logicalRouter = this.getDefaultTier0LogicalRouter_withActiveStandbyMode();
		
		if(!this.isExist(display_name)){
			this.addLogicalRouter(logicalRouter);
		}
		
		if(!this.isExist(display_name)){
			assert false: "Failed to add LogicalRouter_withActiveStandbyMode";
		}
	}
	
	/**
	 * 
	 * @param tierType  Tier0, Tier1
	 */
	public void clean_Default_LogicalRouter(String tierType){
		String display_name = this.get_DisplayName(tierType);

		if(this.isExist(display_name)){
			this.deleteLogicalRouter(display_name);
		}
		
		if(this.isExist(display_name)){
			assert false: "Failed to delete LogicalRouter";
		}
		
		this.cleanPrecondition();
	}
}
