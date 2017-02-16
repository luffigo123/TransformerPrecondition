package com.vmware.transformer.service.Switching;

import java.util.ArrayList;

import com.vmware.transformer.model.Switching.Attachment;
import com.vmware.transformer.model.Switching.LogicalPort;
import com.vmware.transformer.model.Switching.SwitchProfile;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.GetInputString;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class LogicalPortService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	public LogicalSwitchService logicalSwitchService = null;
	public SwitchingProfilesService switchingProfileService = null;
	
	//set default Logical Switch dsplay_name
	public String display_name = "";
	
	public LogicalPortService() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/logical-ports/";
		
//		display_name = "LogicalPort001" + TestData.NativeString;
		display_name = GetInputString.getInputString();
		
		logicalSwitchService = new LogicalSwitchService();
		switchingProfileService = new SwitchingProfilesService();
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
	
	public void addLogicalPort(LogicalPort logicalPort){
		service.addObject(logicalPort, url);
	}
	

	public void modifyLogicalPort(String orgLogicalPortName, LogicalPort newLogicalPort){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgLogicalPortName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		LogicalPort finalObject = newLogicalPort;
		finalObject.set_revision(_revision);
		
		//generate the edit url
		String tzid = this.getObjectId(orgLogicalPortName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);

	}
	
	public void deleteLogicalPort(String displayName){
		String tzid = this.getObjectId(displayName);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	public void deleteLogicalPortWithAttach(String displayName){
		String tzid = this.getObjectId(displayName);
		String deleteUrl = url + tzid + "?detach=true";
		service.deleteObject(deleteUrl);
	}
	
	public String queryLogicalPortInfo(String displayName){
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
	
	public LogicalPort getDefaultLogicalPort(){
		logicalSwitchService = new LogicalSwitchService();
		String defaultLogicalSwitchId = logicalSwitchService.getDefaultLogicalSwitchId();
//		Attachment attachment = new Attachment("id001", "VIF");
//		LogicalPort logicalPort = new LogicalPort(display_name, display_name, defaultLogicalSwitchId, "UP", attachment);
		LogicalPort logicalPort = new LogicalPort(display_name, display_name, defaultLogicalSwitchId, "UP", null);
		return logicalPort;
	}
	
	public String getDefaultLogicalPortId(){
		LogicalPort defaultLogicalPort = this.getDefaultLogicalPort();
		String defaultLogicalPortName = defaultLogicalPort.getDisplay_name();
		if(!this.isExist(defaultLogicalPortName)){
			this.addLogicalPort(defaultLogicalPort);
		}
		
		String defaultLogicalPortId = this.getObjectId(defaultLogicalPortName);
		return defaultLogicalPortId;
	}
	
	/**
	 * 
	 * @param switchProfileType 	QosSwitchingProfile, SwitchSecuritySwitchingProfile, PortMirroringSwitchingProfile, IpDiscoverySwitchingProfile, SpoofGuardSwitchingProfile, none
	 * @return
	 */
	public ArrayList<SwitchProfile> getSwitchingProfileList(String switchProfileType){
		String profileId = "";
		SwitchProfile sp = null;
		ArrayList<SwitchProfile> list = new ArrayList<SwitchProfile>();
		
		switch(switchProfileType){
			case "QosSwitchingProfile":
				profileId = this.switchingProfileService.getObjectId(this.switchingProfileService.qosName);
				sp = new SwitchProfile(profileId, "QosSwitchingProfile");
				list.add(sp);
				break;
			case "SwitchSecuritySwitchingProfile":
				profileId = this.switchingProfileService.getObjectId(this.switchingProfileService.switchSecurityName);
				sp = new SwitchProfile(profileId, "SwitchSecuritySwitchingProfile");
				list.add(sp);
				break;
			case "PortMirroringSwitchingProfile":
				profileId = this.switchingProfileService.getObjectId(this.switchingProfileService.protMirroringName);
				sp = new SwitchProfile(profileId, "PortMirroringSwitchingProfile");
				list.add(sp);
				break;
			case "IpDiscoverySwitchingProfile":
				profileId = this.switchingProfileService.getObjectId(this.switchingProfileService.ipDiscoveryName);
				sp = new SwitchProfile(profileId, "IpDiscoverySwitchingProfile");
				list.add(sp);
				break;
			case "SpoofGuardSwitchingProfile":
				profileId = this.switchingProfileService.getObjectId(this.switchingProfileService.spoofGuardName);
				sp = new SwitchProfile(profileId, "SpoofGuardSwitchingProfile");
				list.add(sp);
				break;
			case "none":
				break;
			default:
				throw new IllegalArgumentException("Invalid type of the SwitchingProfile: " + switchProfileType);
		}

		return list;	
	}
	
	/**
	 * 
	 * @param switchProfileType 	QosSwitchingProfile, SwitchSecuritySwitchingProfile, PortMirroringSwitchingProfile, IpDiscoverySwitchingProfile, SpoofGuardSwitchingProfile, none
	 * @return
	 */
	public LogicalPort getLogicalPort_WithSwitchingProfile(String switchProfileType){
		LogicalPort logicalPort = null;
		
		this.setupPrecondition();

		String defaultLogicalSwitchId = this.logicalSwitchService.getDefaultLogicalSwitchId();
		String admin_state = "UP";
		Attachment attachment = new Attachment("id005", "VIF");
		
		ArrayList<SwitchProfile> switchProfileList = this.getSwitchingProfileList(switchProfileType);
		logicalPort = new LogicalPort(this.display_name, this.display_name, defaultLogicalSwitchId, admin_state, attachment, switchProfileList);
		
		return logicalPort;
	}

	
	public void setupPrecondition(){
		this.logicalSwitchService.setupLogicalSwitch_WithoutSwitchingProfiles();
	}
	
	public void cleanupPrecondition(){
		this.logicalSwitchService.cleanupLogicalSwitch_WithoutSwitchingProfiles();
	}
	
	public void setupLogicalPort(){
		this.setupPrecondition();
		if(!this.isExist(this.display_name)){
			this.addLogicalPort(this.getDefaultLogicalPort());
		}
		
		if(!this.isExist(display_name)){
			assert false: "Failed to Add LogicalPort";
		}
	}
	
	public void cleanupLogicalPort(){
		if(this.isExist(this.display_name)){
			this.deleteLogicalPortWithAttach(this.display_name);
		}
		if(this.isExist(display_name)){
			assert false: "Failed to delete LogicalPort";
		}
		this.cleanupPrecondition();
	}
	
}
