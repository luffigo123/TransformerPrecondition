package com.vmware.transformer.service.Routing;

import java.util.ArrayList;

import com.vmware.transformer.model.Routing.NextHop;
import com.vmware.transformer.model.Routing.StaticRoute;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class StaticRouterServicePreCon {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;

	/**
	 * 
	 * @param tierType		Tier0, Tier1
	 */
	public StaticRouterServicePreCon(String logicalRouterId) {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
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
	 * 
	 * @param staticRouterNetwork
	 * @param nextHopIPAddress
	 * @return
	 */
	public StaticRoute getStaticRoute_NoLogicalRouterPort(String staticRouterNetwork, String nextHopIPAddress){

//		String logicalRoutePort_DisplayName = this.routerPortService.display_name_Downlink;
//		String logicalRoutePortId = this.routerPortService.getObjectId(logicalRoutePort_DisplayName);
//		String nextHop_IPAddress = this.StaticRoute_NextHopIP_ForDownlinkRouterPort;
//		BindingService logical_router_port_id = new BindingService(logicalRoutePort_DisplayName, "true", "LogicalRouterPort", logicalRoutePortId);
		
		String administrative_distance = "1";
		NextHop nexthop = new NextHop(nextHopIPAddress, administrative_distance, null);
		ArrayList<NextHop> next_hops = new ArrayList<NextHop>();
		next_hops.add(nexthop);
		
		StaticRoute staticRoute = new StaticRoute(staticRouterNetwork, "Description", "StaticRoute", null, next_hops);;
		return staticRoute;
	}
	

}
