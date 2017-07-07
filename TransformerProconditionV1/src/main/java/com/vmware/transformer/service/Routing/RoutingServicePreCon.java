package com.vmware.transformer.service.Routing;

import java.util.ArrayList;

import com.vmware.transformer.model.Routing.LogicalRouter;
import com.vmware.transformer.model.Routing.LogicalRouterTier0;
import com.vmware.transformer.model.Routing.LogicalRouterTier0.AdvanceConfig;
import com.vmware.transformer.model.Routing.LogicalRouterTier1;
import com.vmware.transformer.model.Routing.LogicalRouterTier1.AdvanceConfige;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class RoutingServicePreCon {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;

	public RoutingServicePreCon() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/logical-routers/";
				
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
	

	/**
	 * 
	 * @param routerName
	 * @param edgeClusterId
	 * @param high_availability_mode		high_availability_mode  ACTIVE_ACTIVE | ACTIVE_STANDBY
	 * @param internal_transit_network
	 * @return
	 */
	public LogicalRouterTier0 getTier0Router(String routerName,String edgeClusterId, String high_availability_mode, String internal_transit_network, String external_transit_networks){

		ArrayList<String> advanceConfig_list = new ArrayList<String>();
//		advanceConfig_list.add(internal_transit_network);
		advanceConfig_list.add(external_transit_networks);
		AdvanceConfig advance_config = new AdvanceConfig(internal_transit_network, advanceConfig_list);
		
		String router_type = "TIER0";
//		String high_availability_mode = "ACTIVE_ACTIVE";
		LogicalRouterTier0 logicalRouter = new LogicalRouterTier0(routerName, routerName, edgeClusterId, router_type, high_availability_mode, advance_config, null);
		
		return logicalRouter;
	}
	
	
	/**
	 * 
	 * @param routerName
	 * @param edgeClusterId
	 * @param internal_transit_network
	 * @return
	 */
	public LogicalRouterTier1 getTier1Router(String routerName, String edgeClusterId, String internal_transit_network){
//		this.setPrecondition();
//		String edgeClusterId = this.edgeClusterService.getObjectId(this.edgeClusterService.display_name);
		
//		String internal_transit_network = this.IntraTier1TransitNetwork;
		AdvanceConfige advanced_config = new AdvanceConfige(internal_transit_network);
		
		String router_type = "TIER1";
		String high_availability_mode = "ACTIVE_STANDBY";
		
		LogicalRouterTier1 logicalRouter_tier1 = new LogicalRouterTier1(routerName, routerName, edgeClusterId, advanced_config, router_type, high_availability_mode);
		return logicalRouter_tier1;
	}
	
}
