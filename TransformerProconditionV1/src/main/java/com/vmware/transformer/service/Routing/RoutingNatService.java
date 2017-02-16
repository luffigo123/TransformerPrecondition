package com.vmware.transformer.service.Routing;

import com.vmware.transformer.model.Routing.NatRule;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class RoutingNatService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;

	public String tier = "";
	
	public RoutingService routingService = null;
	public String logicalRouterId = "";
	public String logiclaRouter_DisplayName = "";
	
	public String match_source_network = DefaultEnvironment.NAT_source_Network;
	
	/**
	 * 
	 * @param tierType		Tier0, Tier1
	 */
	public RoutingNatService(String tierType) {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		routingService = new RoutingService();
		this.tier = tierType;
		this.init();
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
	
	public void addNatRule(NatRule natRule){
		service.addObject(natRule, url);
	}
	
	/**
	 * 
	 * @param orgNatRuleSourceIPAddress
	 * @param newNatRule
	 */
	public void modifyNatRule(String orgNatRuleSourceNetwork, NatRule newNatRule){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "match_source_network";
		String promptPropertyValue = orgNatRuleSourceNetwork;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		newNatRule.set_revision(_revision);
		NatRule finalObject = newNatRule;
		
		//generate the edit url
		String tzid = this.getObjectId_bySourceIPAddress(orgNatRuleSourceNetwork);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);
	}
	
	/**
	 * 
	 * @param sourceNetwork
	 */
	public void deleteNatRule(String sourceNetwork){
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
	
	public NatRule getDefaultNatRule(){
//		String match_source_network = DefaultEnvironment.NAT_source_Network;
		String translated_network = DefaultEnvironment.NAT_translated_Network;
		String rule_priority = "1024";
		String action = "REFLEXIVE";
		String enabled = "true";
		String logging = "true";
		
		NatRule natRule = new NatRule(match_source_network, translated_network, rule_priority, action, enabled, logging);
		return natRule;
	}
	
	public void setupDefaultNATRule(){
		if(!this.isExist(this.match_source_network)){
			this.addNatRule(this.getDefaultNatRule());
		}
	}
	
	public void cleanupDefaultNatRule(){
		if(this.isExist(this.match_source_network)){
			this.deleteNatRule(this.match_source_network);
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
