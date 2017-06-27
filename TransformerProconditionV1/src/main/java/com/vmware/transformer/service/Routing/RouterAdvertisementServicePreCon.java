package com.vmware.transformer.service.Routing;

import com.vmware.transformer.model.Routing.AdvertiseConfig;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;

public class RouterAdvertisementServicePreCon {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;

//	public String tier = "";
//	
//	public RoutingService routingService = null;
//	public String logicalRouterId = "";
//	public String logiclaRouter_DisplayName = "";


	public RouterAdvertisementServicePreCon(String logicalRouterId) {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/logical-routers/" + logicalRouterId + "/routing/advertisement/";
//		routingService = new RoutingService();
//		this.tier = tierType;
//		this.init();
	}
	
//	/**
//	 * Prepare the pre-contion environment: Logical Router
//	 * @param tier
//	 */
//	public void init(){
//		this.routingService.setupDefault_LogicalRouter(this.tier);
//		logiclaRouter_DisplayName = this.routingService.get_DisplayName(this.tier);
//		logicalRouterId = this.routingService.getObjectId(logiclaRouter_DisplayName);
//		url = "https://" + vmsIP + "/api/v1/logical-routers/" + logicalRouterId + "/routing/advertisement/";
//	}
	
	public void modifyRouteAdvertisetmentConfig(AdvertiseConfig advertiseConfig){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String key ="_revision";
		String valueType = "int";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getJsonItemValue(queryInfo, key, valueType));
		
		advertiseConfig.set_revision(_revision);
		AdvertiseConfig finalObject = advertiseConfig;

		String modifyUrl = url;
		service.modifyObject(finalObject, modifyUrl);
	}

	
	public String queryAllInfo(){
		return service.queryObject(url);
	}
	
	/**
	 * check the Advertisement status whether is enable
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
	
}
