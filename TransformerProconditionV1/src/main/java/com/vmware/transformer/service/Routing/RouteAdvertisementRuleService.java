package com.vmware.transformer.service.Routing;

import java.util.ArrayList;

import com.vmware.transformer.model.Routing.AdvertisementRule;
import com.vmware.transformer.model.Routing.AdvertisementRule.Rule;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.TestData;
import com.vmware.transformer.utils.VerifyUtils;

public class RouteAdvertisementRuleService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;

	public String display_name = "RouteAdvertismentRule001" + TestData.NativeString;
	public String tier = "";
	
	public RoutingService routingService = null;
	public String logicalRouterId = "";
	public String logiclaRouter_DisplayName = "";
	
	/**
	 * 
	 * @param tierType		Tier0, Tier1
	 */
	public RouteAdvertisementRuleService(String tierType) {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		routingService = new RoutingService();
		this.tier = tierType;
		this.init();
	}
	
	/**
	 * Prepare the pre-contion environment: Logical Router
	 * @param tier
	 */
	public void init(){
		this.routingService.setupDefault_LogicalRouter(this.tier);
		
		logiclaRouter_DisplayName = this.routingService.get_DisplayName(this.tier);
		logicalRouterId = this.routingService.getObjectId(logiclaRouter_DisplayName);
		
		url = "https://" + vmsIP + "/api/v1/logical-routers/" + logicalRouterId + "/routing/advertisement/rules/";
		
	}
	
	public void modifyRouteAdvertisementRule(AdvertisementRule newAdvertisementRule){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String key ="_revision";
		String valueType = "int";

		String _revision = String.valueOf(jsonUtils.getJsonItemValue(queryInfo, key, valueType));
		
		newAdvertisementRule.set_revision(_revision);
		AdvertisementRule finalObject = newAdvertisementRule;

		String modifyUrl = url;
		service.modifyObject(finalObject, modifyUrl);

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
		boolean b = verifyUtils.isTargetExist_byObjectType(jsonString, targetPropertyName, displayName, "rules");
		return b;
	}
	
	public AdvertisementRule getDefaultAdvertisementRule(){
		ArrayList<String> networksList = new ArrayList<String>();
		String network01 = DefaultEnvironment.Advertise_network;
		networksList.add(network01);
		
		Rule rule = new Rule(this.display_name, this.display_name, networksList);	
		ArrayList<Rule> rulesList = new ArrayList<Rule>();
		rulesList.add(rule);
		
		AdvertisementRule advertisementRule = new AdvertisementRule(rulesList);

		return advertisementRule;
	}
	
	public void setupDefaultAdvertisementRule(){
		if(!this.isExist(this.display_name)){
			this.modifyRouteAdvertisementRule(this.getDefaultAdvertisementRule());
		}
	}
	
	public void cleanup_defaultRoutingRedistributionRules(){
		if(this.isExist(this.display_name)){
			AdvertisementRule advertisementRule = this.getDefaultAdvertisementRule();
			advertisementRule.setRules(null);
			this.modifyRouteAdvertisementRule(advertisementRule);
		}
	}
	
	public void setPrecondition(){
		this.routingService.setupDefault_LogicalRouter(this.tier);
	}
	
	public void cleanupPrecondition(){
		this.routingService.clean_Default_LogicalRouter(this.tier);
		this.routingService.cleanPrecondition();
	}
}
