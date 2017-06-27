package com.vmware.transformer.service.Routing;

import com.vmware.transformer.model.Routing.BGPConfig;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;

public class RouterBGPServicePreCon {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	/**
	 * 
	 * @param tierType		Tier0, Tier1
	 */
	public RouterBGPServicePreCon(String logicalRouterId) {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/logical-routers/" + logicalRouterId + "/routing/bgp/";
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
	
	public void modifyRouterConfig(BGPConfig bgpConfig){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String key ="_revision";
		String valueType = "int";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getJsonItemValue(queryInfo, key, valueType));
		
		bgpConfig.set_revision(_revision);
		BGPConfig finalObject = bgpConfig;

		String modifyUrl = url;
		service.modifyObject(finalObject, modifyUrl);
	}
	
	public String queryAllInfo(){
		return service.queryObject(url);
	}
	
	/**
	 * check the BGP status whether is enable
	 * @param enabled
	 * @return
	 */
	public boolean isChanged(String enabled){
		boolean isChange = false;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String jsonString = service.queryObject(url);
		String key = "enabled";
		String valueType = "boolean";
		JsonUtils utils = new JsonUtils();
		String result = utils.getJsonItemValue(jsonString, key, valueType);
		if(enabled.equals(result)){
			isChange = true;
		}
		return isChange;
	}
	
	public BGPConfig getBGPConfige(String enabled, String ecmp, String graceful_restart, String as_number, String prefix, String summary_only){
//		String enabled = DefaultEnvironment.BGP_Config_enabled;
//		String ecmp = DefaultEnvironment.BGP_Config_ecmp;
//		String graceful_restart = DefaultEnvironment.BGP_Config_graceful_restart;
//		String as_number = DefaultEnvironment.BGP_Config_AS_number;
//		String prefix = DefaultEnvironment.BGP_Config_prefix;
//		String summary_only = DefaultEnvironment.BGP_Config_summary_only;
		
//		ArrayList<RouteAggregation> raList = new ArrayList<RouteAggregation>();
//		RouteAggregation ra01 = new RouteAggregation(prefix, summary_only);
//		raList.add(ra01);
		BGPConfig bgpConfig = new BGPConfig(enabled, ecmp, graceful_restart, as_number, null);
		return bgpConfig;
	}
	
}
