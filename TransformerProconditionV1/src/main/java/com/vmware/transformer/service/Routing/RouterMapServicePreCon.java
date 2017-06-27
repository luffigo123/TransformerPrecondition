package com.vmware.transformer.service.Routing;

import java.util.ArrayList;

import com.vmware.transformer.model.Routing.MatchCriteria;
import com.vmware.transformer.model.Routing.RouteMap;
import com.vmware.transformer.model.Routing.Sequence;
import com.vmware.transformer.model.Routing.SetCriteria;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class RouterMapServicePreCon {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;

//	public String display_name = "RouteMap001" + TestData.NativeString;
//	public String tier = "";
//	
//	public RoutingService routingService = null;
//	public String logicalRouterId = "";
//	public String logiclaRouter_DisplayName = "";
//	
//	public RoutingIPPrefixListService routingIPPrefixListService = null;


	public RouterMapServicePreCon(String logicalRouterId) {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/logical-routers/" + logicalRouterId + "/routing/route-maps/";
		
//		routingService = new RoutingService();
//		this.tier = tierType;
//		this.init();
	}
//	
//	/**
//	 * Prepare the pre-contion environment: Logical Router
//	 * @param tier
//	 */
//	public void init(){
//		routingIPPrefixListService = new RoutingIPPrefixListService(this.tier);
//		//this.routingService.setupDefault_LogicalRouter(this.tier);
//		
//		logiclaRouter_DisplayName = this.routingService.get_DisplayName(this.tier);
//		logicalRouterId = this.routingService.getObjectId(logiclaRouter_DisplayName);
//		
//		url = "https://" + vmsIP + "/api/v1/logical-routers/" + logicalRouterId + "/routing/route-maps/";
//		
//		//routingIPPrefixListService = new RoutingIPPrefixListService(this.tier);
//	}
	
	public String getObjectId(String displayName){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = displayName;
		String targetPropertyName = "id";
		String tzid = jsonUtils.getPropertyValue(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName);
		return tzid;
	}
	
	public void addRouteMap(RouteMap routMap){
		service.addObject(routMap, url);
	}
	
	/**
	 * 
	 * @param orgIPPrefixList
	 * @param newIPPrefixList
	 */
	public void modifyRouteMap(String orgRouteMapName, RouteMap newRouteMap){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgRouteMapName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision_value = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		newRouteMap.set_revision(_revision_value);
		RouteMap finalObject = newRouteMap;
		
		//generate the edit url
		String tzid = this.getObjectId(orgRouteMapName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);
	}
	
	/**
	 * 
	 * @param displayName
	 */
	public void deleteRouteMap(String displayName){
		String tzid = this.getObjectId(displayName);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	/**
	 * 
	 * @param displayName
	 * @return
	 */
	public String querySpecificRouteMap(String displayName){
		String id = this.getObjectId(displayName);
		String queryUrl = url + id;
		return service.queryObject(queryUrl);
	}
	
	public String queryAllInfo(){
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
	
	public RouteMap getRouteMap(String routerMapName, ArrayList<String> ipPrefixListId_List){

		String multi_exit_discriminator = DefaultEnvironment.routeMap_MED;
		String weight = DefaultEnvironment.routeMap_weight;
		String community = DefaultEnvironment.routeMap_community;
		String as_path_prepend = DefaultEnvironment.routeMap_AS;
		String action = DefaultEnvironment.routMap_action;

		SetCriteria set_creteria = new SetCriteria(multi_exit_discriminator, weight, community, as_path_prepend);
		
		MatchCriteria match_criteria = new MatchCriteria(ipPrefixListId_List);
		
		ArrayList<Sequence> sequenceList = new ArrayList<Sequence>();
		Sequence sequence01 = new Sequence(match_criteria, set_creteria, action);
		sequenceList.add(sequence01);
		
		RouteMap routeMap = new RouteMap(routerMapName, routerMapName, sequenceList);
		return routeMap;
	}
	
	

}
