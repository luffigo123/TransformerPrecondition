package com.vmware.transformer.service.Routing;

import java.util.ArrayList;

import com.vmware.transformer.model.Routing.BindingService;
import com.vmware.transformer.model.Routing.NextHop;
import com.vmware.transformer.model.Routing.StaticRoute;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.GetInputString;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;


public class StaticRouterService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;

//	public String display_name = "StaticRouter001" + TestData.NativeString;
	public String display_name;
	public String tier = "";
	
	public RoutingService routingService = null;
	public RouterPortService routerPortService = null;
	
	public String StaticRoute_NextHopIP_ForUplinkRouterPort = DefaultEnvironment.StaticRoute_NextHopIP_Tier0AA;
	public String StaticRoute_NextHopIP_ForDownlinkRouterPort = DefaultEnvironment.StaticRoute_NextHopIP_ForDownlinkRouterPort;
	public String staticRoute_Network = DefaultEnvironment.staticRoute_Network;
	
	
	/**
	 * 
	 * @param tierType		Tier0, Tier1
	 */
	public StaticRouterService(String tierType) {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		
		routingService = new RoutingService();
		routerPortService = new RouterPortService();
		this.tier = tierType;
		this.init();
		
		display_name = GetInputString.getInputString();
	}
	
	/**
	 * Prepare the pre-contion environment: Logical Router, Route Port
	 * @param tier
	 */
	public void init(){
		String logicalRouterId = "";
		String logiclaRouter_DisplayName = "";
		this.routerPortService.setup_DefaultDownlink_LogicalRouterPort_OnRouter(this.tier);
		logiclaRouter_DisplayName = this.routingService.get_DisplayName(this.tier);
		logicalRouterId = this.routingService.getObjectId(logiclaRouter_DisplayName);
		
		url = "https://" + vmsIP + "/api/v1/logical-routers/" + logicalRouterId + "/routing/static-routes/";
	}
	
	/**
	 * get the objectId by network
	 * @param staticRouteNetwork
	 * @return
	 */
	public String getObjectId(String staticRouteNetwork){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "network";
		String promptPropertyValue = staticRouteNetwork;
		String targetPropertyName = "id";
		String tzid = jsonUtils.getPropertyValue(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName);
		return tzid;
	}
	
	public void addStaticRouter(StaticRoute staticRoute){
		service.addObject(staticRoute, url);
	}
	

	public void modifyStaticRoute(String orgNetwork, StaticRoute newStaticRoute){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "network";
		String promptPropertyValue = orgNetwork;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision_value = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		newStaticRoute.set_revision(_revision_value);
		StaticRoute finalObject = newStaticRoute;
		
		//generate the edit url
		String tzid = this.getObjectId(orgNetwork);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);
	}
	
	
	public void deleteStaticRoute(String staticRouteNetwork){
		String tzid = this.getObjectId(staticRouteNetwork);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	
	public String querySpecificStaticRouterPort(String displayName){
		String id = this.getObjectId(displayName);
		String queryUrl = url + id;
		return service.queryObject(queryUrl);
	}
	
	public String queryAllInfo(){
		return service.queryObject(url);
	}
	
	/**
	 * check the static route whether exist by network
	 * @param staticRouteNetwort
	 * @return
	 */
	public boolean isExist(String staticRouteNetwort){
try {
	Thread.sleep(1000);
} catch (InterruptedException e) {
	e.printStackTrace();
}
		VerifyUtils verifyUtils = new VerifyUtils();
		String jsonString = service.queryObject(url);
		String targetPropertyName = "network";
		boolean b = verifyUtils.isTargetExist(jsonString, targetPropertyName, staticRouteNetwort);
		return b;
	}
	
	/**
	 * Get default StaticRoute with Downlink RoutePort
	 * @return
	 */
	public StaticRoute getDefaultStaticRoute_WithDownlinkPort(){

		String logicalRoutePort_DisplayName = this.routerPortService.display_name_Downlink;
		String logicalRoutePortId = this.routerPortService.getObjectId(logicalRoutePort_DisplayName);
		String nextHop_IPAddress = this.StaticRoute_NextHopIP_ForDownlinkRouterPort;
		
		BindingService logical_router_port_id = new BindingService(logicalRoutePort_DisplayName, "true", "LogicalRouterPort", logicalRoutePortId);
		
		String administrative_distance = "1";
		NextHop nexthop = new NextHop(nextHop_IPAddress, administrative_distance, logical_router_port_id);
		ArrayList<NextHop> next_hops = new ArrayList<NextHop>();
		next_hops.add(nexthop);
		
		String staticRouteNetwork = this.staticRoute_Network;
		
		StaticRoute staticRoute = new StaticRoute(staticRouteNetwork, "Description", "StaticRoute", null, next_hops);;
		
		return staticRoute;
	}
	
	/**
	 * set up the environment of StaticRoute with DownlinkRoutePort
	 */
	public void setupDefault_StaticRoute_WithDownlinkPort_ENV(){
		if(!this.isExist(this.staticRoute_Network)){
			this.addStaticRouter(this.getDefaultStaticRoute_WithDownlinkPort());
		}
		
		if(!this.isExist(this.staticRoute_Network)){
			assert false: "Failed to add StaticRoute_withDownLinkPort";
		}
		
	}
	
	/**
	 * Clean up the environment of StaticRoute with DownlinkRoutePort 
	 */
	public void cleanUpDefault_StaticRoute_WithDownlinkPort_ENV(){
		if(this.isExist(this.staticRoute_Network)){
			this.deleteStaticRoute(this.staticRoute_Network);
		}
		
		if(this.isExist(this.staticRoute_Network)){
			assert false: "Failed to delete StaticRoute_withDownLinkPort";
		}
		this.cleanPreCondition_Downlink(this.tier);
	}
	
	/**
	 * 
	 * @param tierType
	 */
	public void cleanPreCondition_Downlink(String tierType){
		this.routerPortService.clean_DefaultDownlink_LogicalRouterPort_OnRouter(tierType);
	}
	

	
}
