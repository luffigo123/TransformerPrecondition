package com.vmware.transformer.service.Routing;

import com.vmware.transformer.model.Routing.RouteNatRule;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class NATService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;

	public String tier = "";
	
	public RoutingService routingService = null;
	public String logicalRouterId = "";
	public String logiclaRouter_DisplayName = "";
	
	public String match_source_network = DefaultEnvironment.Tier1RouterLeft_SNAT_SourceNetwork;

	/**
	 * 
	 * @param tierType		Tier0, Tier1
	 */
	public NATService(String tierType) {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		routingService = new RoutingService();
		this.tier = tierType;
		this.init();
	}
	

	/**
	 * 
	 * @param tierType				Tier0, Tier
	 * @param logicalRouteMode		ActiveStandby
	 */
	public NATService(String tierType, String logicalRouteMode) {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		routingService = new RoutingService();
		this.tier = tierType;
		if("ActiveStandby".equals(logicalRouteMode)){
			this.init_withMode();
		}
	}
	
	/**
	 * Prepare the pre-condition environment: Logical Router
	 * @param tier
	 */
	public void init(){
		this.routingService.setupDefault_LogicalRouter(this.tier);
		logiclaRouter_DisplayName = this.routingService.get_DisplayName(this.tier);
		logicalRouterId = this.routingService.getObjectId(logiclaRouter_DisplayName);
		
		url = "https://" + vmsIP + "/api/v1/logical-routers/" + logicalRouterId + "/nat/rules/";
		
	}
	
	/**
	 * Prepare the pre-condition environment: Logical Router
	 * @param tier
	 */
	public void init_withMode(){
		this.routingService.setupDefaultLogicalRouter_withActiveStandbyMode(this.tier);
		logiclaRouter_DisplayName = this.routingService.get_DisplayName(this.tier);
		logicalRouterId = this.routingService.getObjectId(logiclaRouter_DisplayName);
		
		url = "https://" + vmsIP + "/api/v1/logical-routers/" + logicalRouterId + "/nat/rules/";
	}
	
	
	/**
	 * 
	 * @param sourceNetwork
	 * @return
	 */
	public String getObjectId_bySourceIPAddress(String sourceNetwork){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "match_source_network";
		String promptPropertyValue = sourceNetwork;
		String targetPropertyName = "id";
		String tzid = jsonUtils.getPropertyValue(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName);
		return tzid;
	}
	
	public void addRouteNatRule(RouteNatRule routeNatRule){
		service.addObject(routeNatRule, url);
	}
	
	/**
	 * 
	 * @param orgNatRuleSourceIPAddress
	 * @param newNatRule
	 */
	public void modifyNatRule(String orgRouteNatRule_SourceNetwork, RouteNatRule newRouteNatRule){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "match_source_network";
		String promptPropertyValue = orgRouteNatRule_SourceNetwork;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		newRouteNatRule.set_revision(_revision);
		RouteNatRule finalObject = newRouteNatRule;
		
		//generate the edit url
		String tzid = this.getObjectId_bySourceIPAddress(orgRouteNatRule_SourceNetwork);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);
	}
	
	/**
	 * 
	 * @param sourceNetwork
	 */
	public void deleteRouteNatRule(String sourceNetwork){
		String tzid = this.getObjectId_bySourceIPAddress(sourceNetwork);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	/**
	 * 
	 * @param sourceNetwork
	 * @return
	 */
	public String querySpecificNatRule(String sourceNetwork){
		String id = this.getObjectId_bySourceIPAddress(sourceNetwork);
		String queryUrl = url + id;
		return service.queryObject(queryUrl);
	}
	
	public String queryAllInfo(){
		return service.queryObject(url);
	}
	
	/**
	 * check the specific object whether exist by sourceNetwork
	 * @param displayName
	 * @return
	 */
	public boolean isExist(String sourceNetwork){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		VerifyUtils verifyUtils = new VerifyUtils();
		String jsonString = service.queryObject(url);
		String targetPropertyName = "match_source_network";
		boolean b = verifyUtils.isTargetExist(jsonString, targetPropertyName, sourceNetwork);
		return b;
	}
	
	/**
	 * 
	 * @param type	SNAT, DNAT,NO_NAT
	 * @return
	 */
	public RouteNatRule getDefaultRouteNatRule(String type){
		String match_destination_network = DefaultEnvironment.Tier1RouterLeft_SNAT_DestinationNetwork;
		String translated_network = DefaultEnvironment.Tier1RouterLeft_SNAT_TranslatedNetwork;
		String rule_priority = "1024";
		String action = type;
		String enabled = "true";
		String logging = "true";
		
		if("NO_NAT".equals(type)){
			translated_network = "";
		}
		
		RouteNatRule routeNatRule = new RouteNatRule(rule_priority, action, match_source_network, match_destination_network, translated_network,enabled, logging);
		return routeNatRule;
	}
	
	/**
	 * 
	 * @param type SNAT, DNAT,NO_NAT
	 */
	public void setupDefaultNATRule(String type){
		if(!this.isExist(this.match_source_network)){
			this.addRouteNatRule(this.getDefaultRouteNatRule(type));
		}
	}
	
	public void cleanupDefaultNatRule(){
		if(this.isExist(this.match_source_network)){
			this.deleteRouteNatRule(this.match_source_network);
		}
	}
	
	public void setPrecondition(String tierType){
		this.routingService.setupDefault_LogicalRouter(tierType);
	}
	
	public void cleanupPrecondition(String tierType){
		this.routingService.clean_Default_LogicalRouter(tierType);
		this.routingService.cleanPrecondition();
	}
}
