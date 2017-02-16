package com.vmware.transformer.service.Routing;

import com.vmware.transformer.model.Routing.BFDConfig;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;

public class RoutingBFDConfigService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;

	public String tier = "";
	
	public RoutingService routingService = null;
	public String logicalRouterId = "";
	public String logiclaRouter_DisplayName = "";

	/**
	 * 
	 * @param tierType		Tier0, Tier1
	 */
	public RoutingBFDConfigService(String tierType) {
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
		url = "https://" + vmsIP + "/api/v1/logical-routers/" + logicalRouterId + "/routing/bfd-config/";
	}
	
	public void modifyRoutingBFDConfig(BFDConfig bdfConfig){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String key ="_revision";
		String valueType = "int";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getJsonItemValue(queryInfo, key, valueType));
		
		bdfConfig.set_revision(_revision);
		BFDConfig finalObject = bdfConfig;

		String modifyUrl = url;
		service.modifyObject(finalObject, modifyUrl);
	}

	
	public String queryAllInfo(){
		return service.queryObject(url);
	}
	
	/**
	 * check the forwarding_up_timer whether be changed
	 * @param forwarding_up_timer
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
	
	public void setPrecondition(String tierType){
		this.routingService.setupDefault_LogicalRouter(tierType);
	}
	
	public void cleanupPrecondition(String tierType){
		this.routingService.clean_Default_LogicalRouter(tierType);
		this.routingService.cleanPrecondition();
	}
}
