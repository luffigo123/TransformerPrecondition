package com.vmware.transformer.service.Routing;

import com.vmware.transformer.model.Routing.NatRule;
import com.vmware.transformer.model.Routing.RouteNatRule;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class NATServicePreCon {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;

	public NATServicePreCon(String logicalRouterId) {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
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
	
	public void addReflexiveNATRule(NatRule reflexiveNATRule){
		service.addObject(reflexiveNATRule, url);
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
	 * @param type					SNAT, DNAT
	 * @param sourceNetwork
	 * @param destinationNetwork
	 * @param translatedNetwork
	 * @return
	 */
	public RouteNatRule getNatRule(String type, String sourceNetwork, String destinationNetwork, String translatedNetwork){

		String rule_priority = "1024";
		String action = type;
		String enabled = "true";
		String logging = "true";
		
		RouteNatRule routeNatRule = new RouteNatRule(rule_priority, action, sourceNetwork, destinationNetwork, translatedNetwork,enabled, logging);
		return routeNatRule;
	}
	
	/**
	 * 
	 * @param sourceNetwork
	 * @param translatedNetwork
	 * @return
	 */
	public NatRule getReflexiveNatRule(String sourceNetwork, String translatedNetwork){
		String rule_priority = "1024";
		String action = "REFLEXIVE";
		String enabled = "true";
		String logging = "true";
		
		NatRule natRule = new NatRule(sourceNetwork, translatedNetwork, rule_priority, action, enabled, logging);
		return natRule;
	}
}
