package com.vmware.transformer.service.Routing;

import java.util.ArrayList;

import com.vmware.transformer.model.Routing.RoutingRedistributionRules;
import com.vmware.transformer.model.Routing.Rule;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class RouterRedistributionRuleServicePreCon {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;

	/**
	 * 
	 * @param tierType		Tier0, Tier1
	 */
	public RouterRedistributionRuleServicePreCon(String logicalRouterId) {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/logical-routers/" + logicalRouterId + "/routing/redistribution/rules/";	
	}

	
	public void modifyRoutingRedistributionRules(RoutingRedistributionRules newRoutingRedistributionRules){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String key ="_revision";
		String valueType = "int";

		String _revision = String.valueOf(jsonUtils.getJsonItemValue(queryInfo, key, valueType));
		
		newRoutingRedistributionRules.set_revision(_revision);
		RoutingRedistributionRules finalObject = newRoutingRedistributionRules;

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
	
	public RoutingRedistributionRules getRouteRedistributionRules(String redistributionCriteriaName,String routeMapId){
//		String routeMapId = this.routingRouteMapService.getObjectId(this.routingRouteMapService.display_name); 
		ArrayList<String> sourceList = new ArrayList<String>();
		sourceList.add("STATIC");
		sourceList.add("NSX_CONNECTED");
		sourceList.add("NSX_STATIC");
		sourceList.add("TIER0_NAT");
		sourceList.add("TIER1_NAT");
		Rule rule01 = new Rule(redistributionCriteriaName, redistributionCriteriaName, sourceList, "BGP", routeMapId);;
		ArrayList<Rule> ruleList = new ArrayList<Rule>();
		ruleList.add(rule01);
		RoutingRedistributionRules redistributionRule = new RoutingRedistributionRules(ruleList);
		return redistributionRule;
	}

}
