package com.vmware.transformer.service.Switching;

import com.vmware.transformer.model.Switching.LogicalSwitch;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class LogicalSwitchServicePreCon {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;

	public LogicalSwitchServicePreCon() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/logical-switches/";
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
	
	public void addLogicalSwitch(LogicalSwitch logicalSwitch){
		service.addObject(logicalSwitch, url);
	}
	


	public void deleteLogicalSwitch(String displayName){
		String tzid = this.getObjectId(displayName);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	
	public String queryLogicalSwitchInfo(String displayName){
		String tzid = this.getObjectId(displayName);
		String queryUrl = url + tzid;
		return service.queryObject(queryUrl);
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
	
	public String getSpecificLogicalSwitchInfo(String logicalSwitchName){
		//GET https://<nsx-mgr>/api/v1/logical-switches/cc5ff938-6f09-4841-8f0f-294e86415472/state
		String id = this.getObjectId(logicalSwitchName);
		String queryUrl = url + id + "/state";
		return service.queryObject(queryUrl);
	}
	
	/**
	 * verify the logicalSwitch status whether is success during the time
	 * @param minutes
	 * @param transNodeName
	 * @param key			state
	 * @param valueType		int | string | boolean
	 * @return
	 */
	public boolean isLogicalSwitchReady(int minutes, String logicalSwitchName, String key, String valueType){
		boolean result = false;
		String queryInfo = "";
		JsonUtils util = new JsonUtils();
		int begin = 0;
		while(begin <= (minutes * 60)){
			queryInfo = this.getSpecificLogicalSwitchInfo(logicalSwitchName);
			String state = util.getJsonItemValue(queryInfo, key, valueType);
			if("success".equalsIgnoreCase(state)){
				result = true;
				break;
			}else{
				try {
					Thread.sleep(20000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				begin = begin + 20;
			}
		}
		return result;
	}
	
	
	public LogicalSwitch getLogicalSwitch(String logicalSwitchName, String transportZoneId){
		LogicalSwitch defaultLS = new LogicalSwitch(logicalSwitchName, logicalSwitchName, transportZoneId, "UP", "MTEP");
		return defaultLS;
	}
	
	/**
	 * check the switch status whether installed successfully during the time
	 * @param minutes
	 * @param switchId
	 * @param key
	 * @param valueType				string | int | boolean
	 * @param checkItemStatusValue  pending, in_progress, success, failed, partial_success, orphaned
	 * @return
	 */
	public boolean isSwitchReady(int minutes, String switchId, String key, String valueType, String checkItemStatusValue){
		boolean result = false;
		JsonUtils util = new JsonUtils();
		String url = this.url + switchId + "/state";
		String queryInfo = "";
		int begin = 0;
		while(begin <= (minutes * 60)){
			queryInfo = service.queryObject(url);
			String keyValue = util.getJsonItemValue(queryInfo, key, valueType);
			if(checkItemStatusValue.equalsIgnoreCase(keyValue)){
				result = true;
				break;
			}else{
				try {
					Thread.sleep(20000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				begin = begin + 20;
			}
		}
		return result;
	}
	
	
}
