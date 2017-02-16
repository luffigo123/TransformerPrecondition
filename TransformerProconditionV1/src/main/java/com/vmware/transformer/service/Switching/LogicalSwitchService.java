package com.vmware.transformer.service.Switching;

import java.util.ArrayList;

import com.vmware.transformer.model.Fabric.TransportZone;
import com.vmware.transformer.model.Switching.LogicalSwitch;
import com.vmware.transformer.model.Switching.SwitchProfile;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.service.Fabric.TransportNodeService;
import com.vmware.transformer.service.Fabric.TransportZoneService;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.GetInputString;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class LogicalSwitchService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	public String display_name;

	public SwitchingProfilesService switchingProfileService = null;
	public TransportZoneService transprotZoneService = null;
	
	public TransportNodeService transNodeService = null;

	public LogicalSwitchService() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/logical-switches/";
		
//		display_name = "LogicalSwitch" + TestData.NativeString;
		
		display_name = GetInputString.getInputString();
		
		switchingProfileService = new SwitchingProfilesService();
		transprotZoneService = new TransportZoneService();
		transNodeService = new TransportNodeService();
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
	
	/**
	 * 
	 * @param orgLogicalSwitchName
	 * @param newLogicalSwitch
	 */
	public void modifyLogicalSwitch(String orgLogicalSwitchName, LogicalSwitch newLogicalSwitch){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgLogicalSwitchName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));

		LogicalSwitch finalObject = newLogicalSwitch;
		finalObject.set_revision(_revision);
		
		//generate the edit url
		String tzid = this.getObjectId(orgLogicalSwitchName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);

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
	
	
	public String getTransportZoneId(){
		TransportZone defaultTZ = transprotZoneService.getDefaultTransportZone();
		String defualtTZName = transprotZoneService.display_name;
		if(!transprotZoneService.isExist(defualtTZName)){
			transprotZoneService.addTransportZone(defaultTZ);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String tzId = transprotZoneService.getTransportZoneId(defualtTZName);
		return tzId;
	}
	
	
	public LogicalSwitch getDefaultLogicalSwitch(){
		LogicalSwitch defaultLS = new LogicalSwitch(display_name, display_name, this.getTransportZoneId(), "UP", "MTEP");
		return defaultLS;
	}
	
	
	public String getDefaultLogicalSwitchId(){
		LogicalSwitch defaultLS = this.getDefaultLogicalSwitch();
		String defaultLS_Name = defaultLS.getDisplay_name();
		if(!this.isExist(defaultLS_Name)){
			this.addLogicalSwitch(defaultLS);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String defaultLS_Id = this.getObjectId(defaultLS_Name);
		return defaultLS_Id;
	}

	/**
	 * 
	 * @param switchProfileType		QosSwitchingProfile, SwitchSecuritySwitchingProfile, PortMirroringSwitchingProfile, IpDiscoverySwitchingProfile, SpoofGuardSwitchingProfile
	 * @return
	 */
	public LogicalSwitch getLogicalSwitch_WithUserSwitchingProfile(String switchProfileType){
		String tranportZoneId = this.transprotZoneService.getDefaultTransportZoneId();
		String admin_state = "UP";
		String replication_mode = "MTEP";
		
		String profileId = "";
		SwitchProfile sp = null;
		
		switch(switchProfileType){
			case "QosSwitchingProfile":
				profileId = this.switchingProfileService.getObjectId(this.switchingProfileService.qosName);
				sp = new SwitchProfile(profileId, "QosSwitchingProfile");
				break;
			case "SwitchSecuritySwitchingProfile":
				profileId = this.switchingProfileService.getObjectId(this.switchingProfileService.switchSecurityName);
				sp = new SwitchProfile(profileId, "SwitchSecuritySwitchingProfile");
				break;
			case "PortMirroringSwitchingProfile":
				profileId = this.switchingProfileService.getObjectId(this.switchingProfileService.protMirroringName);
				sp = new SwitchProfile(profileId, "PortMirroringSwitchingProfile");
				break;
			case "IpDiscoverySwitchingProfile":
				profileId = this.switchingProfileService.getObjectId(this.switchingProfileService.ipDiscoveryName);
				sp = new SwitchProfile(profileId, "IpDiscoverySwitchingProfile");
				break;
			case "SpoofGuardSwitchingProfile":
				profileId = this.switchingProfileService.getObjectId(this.switchingProfileService.spoofGuardName);
				sp = new SwitchProfile(profileId, "SpoofGuardSwitchingProfile");
				break;
			default:
				throw new IllegalArgumentException("Invalid type of the SwitchingProfile: " + switchProfileType);
		}
		
		ArrayList<SwitchProfile> list = new ArrayList<SwitchProfile>();
		list.add(sp);
		
		LogicalSwitch logicalSwitch = new LogicalSwitch(this.display_name, this.display_name, tranportZoneId, admin_state, replication_mode, list);
		
		return logicalSwitch;
	}
	
	public void setupPreCondition(){
		switchingProfileService.setupSwitchingProfiles();
		transprotZoneService.setupDefaultTransportZone();
	}
	
	public void cleanPreCondition(){
		switchingProfileService.cleanSwitchingProfiles();
		transprotZoneService.cleanDefaultTransportZone();
	}
	
	public void setupLogicalSwitch_WithoutSwitchingProfiles(){
		this.setupPreCondition();
		if(!this.isExist(this.display_name)){
			this.addLogicalSwitch(this.getDefaultLogicalSwitch());	
		}
		
		if(!this.isExist(display_name)){
			assert false: "Failed to Add LogicalSwitch";
		}
	}
	
	public void cleanupLogicalSwitch_WithoutSwitchingProfiles(){
		if(this.isExist(this.display_name)){
			this.deleteLogicalSwitch(this.display_name);
		}
		
		if(this.isExist(display_name)){
			assert false: "Failed to delete LogicalSwitch";
		}
		this.cleanPreCondition();
	}
	
	
	/**
	 * 
	 * @param switchProfileType		QosSwitchingProfile, SwitchSecuritySwitchingProfile, PortMirroringSwitchingProfile, IpDiscoverySwitchingProfile, SpoofGuardSwitchingProfile
	 */
	public void setupLogicalSwitch_WithUserSwitchingProfile(String switchProfileType){
		
		LogicalSwitch logicalSwitch = this.getLogicalSwitch_WithUserSwitchingProfile(switchProfileType);
		
		if(!this.isExist(this.display_name)){
			this.addLogicalSwitch(logicalSwitch);	
		}
	}
	
	public void setupPreCondition_withTransNode(){
		this.transNodeService.setup_defaultTransNode_withESXiHost_ByCommand();
	}
	
	public void cleanPreCondition_withTransNode(){
		this.transNodeService.cleanup_defaultTransNode_withESXiHost_ByCommand();
	}
	
}
