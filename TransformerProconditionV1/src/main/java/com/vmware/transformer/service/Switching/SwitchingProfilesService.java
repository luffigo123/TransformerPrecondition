package com.vmware.transformer.service.Switching;

import java.util.ArrayList;

import com.vmware.transformer.model.Switching.SwitchingProfile;
import com.vmware.transformer.model.Switching.SwitchingProfile_IPDiscovery;
import com.vmware.transformer.model.Switching.SwitchingProfile_PortMirroring;
import com.vmware.transformer.model.Switching.SwitchingProfile_QoS;
import com.vmware.transformer.model.Switching.SwitchingProfile_QoS.Dscp;
import com.vmware.transformer.model.Switching.SwitchingProfile_QoS.Shape;
import com.vmware.transformer.model.Switching.SwitchingProfile_SpoofGuard;
import com.vmware.transformer.model.Switching.SwitchingProfile_SwitchSecurity;
import com.vmware.transformer.model.Switching.SwitchingProfile_SwitchSecurity.BPDU_filter;
import com.vmware.transformer.model.Switching.SwitchingProfile_SwitchSecurity.DHCP_Filter;
import com.vmware.transformer.model.Switching.SwitchingProfile_SwitchSecurity.Rate_Limits;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.GetInputString;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;


public class SwitchingProfilesService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	public String display_name = "";
	public String ipDiscoveryName = "";
	public String protMirroringName = "";
	public String qosName = "";
	public String spoofGuardName = "";
	public String switchSecurityName = "";
	
	
	public SwitchingProfilesService() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/switching-profiles/";
		
//		display_name = "SwitchingProfiles" + TestData.NativeString;
//		ipDiscoveryName = "SwitchingProfile_IPDiscovery" + TestData.NativeString;
//		protMirroringName = "SwitchingProfile_PortMirroring" + TestData.NativeString;
//		qosName = "SwitchingProfile_QoS" + TestData.NativeString;
//		spoofGuardName = "SwitchingProfile_SpoofGuard" + TestData.NativeString;
//		switchSecurityName = "SwitchingProfile_SwitchSecurity" + TestData.NativeString;
		
		display_name = GetInputString.getInputString();
		ipDiscoveryName = "SwitchingProfile_IPDiscovery" + display_name;
		protMirroringName = "SwitchingProfile_PortMirroring" + display_name;
		qosName = "SwitchingProfile_QoS" + display_name;
		spoofGuardName = "SwitchingProfile_SpoofGuard" + display_name;
		switchSecurityName = "SwitchingProfile_SwitchSecurity" + display_name;
	}
	
	public SwitchingProfile_IPDiscovery getDefaultSwitchingProfile_IPDiscovery(){
		SwitchingProfile_IPDiscovery ipDiscovery = new SwitchingProfile_IPDiscovery(ipDiscoveryName, ipDiscoveryName, "IpDiscoverySwitchingProfile", "true", "true");
		return ipDiscovery;
	}
	
	public SwitchingProfile_PortMirroring getDefaultSwitchingProfile_PortMirroring(){
		ArrayList<String> destinations = new ArrayList<String>();
		destinations.add("192.168.1.2");
		destinations.add("192.168.2.2");
		
		SwitchingProfile_PortMirroring portMirroring = new SwitchingProfile_PortMirroring(protMirroringName, protMirroringName, "PortMirroringSwitchingProfile", destinations, "7", "BIDIRECTIONAL");
		return portMirroring;
	}
	
	public SwitchingProfile_QoS getDefaultSwitchingProfile_QoS(){
		ArrayList<String> required_capabilities = new ArrayList<String>();
		required_capabilities.add("switchingprofile.qos.shaper.egress");
		
		SwitchingProfile_QoS.Dscp dscp =  new Dscp("TRUSTED", "0");
		
		SwitchingProfile_QoS.Shape shape1 = new Shape("IngressRateShaper", "true", "1", "2", "1");
//		SwitchingProfile_QoS.Shape shape2 = new Shape("IngressBroadcastRateShaper", "true", 1, 2, 1);
		SwitchingProfile_QoS.Shape shape3 = new Shape("EgressRateShaper", "true", "1", "2", "1");
		ArrayList<Shape> shaper_configuration = new ArrayList<Shape>();

		shaper_configuration.add(shape1);
//		shaper_configuration.add(shape2);
		shaper_configuration.add(shape3);
		
		SwitchingProfile_QoS qos = new SwitchingProfile_QoS(qosName, qosName, "QosSwitchingProfile", required_capabilities, "7", dscp, shaper_configuration);
		return qos;
	}
	
	public SwitchingProfile_SpoofGuard getDefaultSwitchingProfile_SpoofGuard(){
		ArrayList<String> white_list_providers = new ArrayList<String>();
//		white_list_providers.add("LPORT_BINDINGS");
//		white_list_providers.add("LSWITCH_BINDINGS");
		SwitchingProfile_SpoofGuard spoofGuard = new SwitchingProfile_SpoofGuard(spoofGuardName, spoofGuardName, "SpoofGuardSwitchingProfile", white_list_providers);
		return spoofGuard;
	}
	
	public SwitchingProfile_SwitchSecurity getDefaultSwitchingProfile_SwitchSecurity(){
		
		ArrayList<String> required_capabilities = new ArrayList<String>();
		required_capabilities.add("switchingprofile.switch-security.rate-limiting");
		
		ArrayList<String> white_list = new ArrayList<String>();
		white_list.add("01:80:c2:00:00:0a");
		white_list.add("01:00:0c:cc:cc:cd");
		SwitchingProfile_SwitchSecurity.BPDU_filter bpdu_filter = new BPDU_filter("true", white_list);
		
		SwitchingProfile_SwitchSecurity.DHCP_Filter dhcp_filter = new DHCP_Filter("true", "true");
		
		SwitchingProfile_SwitchSecurity.Rate_Limits rate_limits = new Rate_Limits("2", "2", "1", "1", "true");
		
		SwitchingProfile_SwitchSecurity switchSecurity = new SwitchingProfile_SwitchSecurity("SwitchSecuritySwitchingProfile", switchSecurityName, switchSecurityName, required_capabilities, bpdu_filter, "true", dhcp_filter, rate_limits);
	
		return switchSecurity;
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
	
	public void addSwitchingProfile(SwitchingProfile switchingProfile){
		service.addObject(switchingProfile, url);
	}
	

	/**
	 * 
	 * @param orgSwitchingProfileName
	 * @param newSwitchingProfile
	 */
	public void modifySwitchingProfile(String orgSwitchingProfileName, SwitchingProfile newSwitchingProfile){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgSwitchingProfileName;
		String targetPropertyName ="_revision";
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		int index = "class com.vmware.bumblebee.model.Switches.".length()+1;
		String switchProfileType = newSwitchingProfile.getClass().toString().substring(index);
		
		SwitchingProfile finalObject = null;
		String tzid = this.getObjectId(orgSwitchingProfileName);

		switch (switchProfileType){
			case "SwitchingProfile_IPDiscovery":
				SwitchingProfile_IPDiscovery ipDiscovery = (SwitchingProfile_IPDiscovery)newSwitchingProfile;
				ipDiscovery.set_revision(_revision);
				finalObject = ipDiscovery;
				break;
				
			case "SwitchingProfile_PortMirroring":
				SwitchingProfile_PortMirroring portMirroring = (SwitchingProfile_PortMirroring)newSwitchingProfile;
				portMirroring.set_revision(_revision);
				finalObject = portMirroring;
				break;
				
			case "SwitchingProfile_QoS":
				SwitchingProfile_QoS qos = (SwitchingProfile_QoS)newSwitchingProfile;
				qos.set_revision(_revision);
				finalObject = qos;
				break;
				
			case "SwitchingProfile_SpoofGuard":
				SwitchingProfile_SpoofGuard spoofGuard = (SwitchingProfile_SpoofGuard)newSwitchingProfile;
				spoofGuard.set_revision(_revision);
				finalObject = spoofGuard;
				break;
				
			case "SwitchingProfile_SwitchSecurity":
				SwitchingProfile_SwitchSecurity switchSecurity = (SwitchingProfile_SwitchSecurity)newSwitchingProfile;
				switchSecurity.set_revision(_revision);
				finalObject = switchSecurity;
				break;
			default:
				throw new IllegalArgumentException("Invalid type of the SwitchingProfile: " + switchProfileType);
		}
		//generate the edit url
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);

	}
	
	
	public void deleteSwitchingProfile(String displayName){
		String tzid = this.getObjectId(displayName);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	
	public String querySwitchingProfileInfo(String displayName){
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
	
	public void setupSwitchingProfiles(){
		
		SwitchingProfile switchingProfile = null;
		
		if(!this.isExist(ipDiscoveryName)){
			switchingProfile = this.getDefaultSwitchingProfile_IPDiscovery();
			this.addSwitchingProfile(switchingProfile);
			
			if(!this.isExist(ipDiscoveryName)){
				assert false: "Failed to add SwitchingProfile_ipDiscovery";
			}
		}
		
		if(!this.isExist(protMirroringName)){
			switchingProfile = this.getDefaultSwitchingProfile_PortMirroring();
			this.addSwitchingProfile(switchingProfile);
			
			if(!this.isExist(protMirroringName)){
				assert false: "Failed to add SwitchingProfile_protMirroring";
			}
		}
		
		if(!this.isExist(spoofGuardName)){
			switchingProfile = this.getDefaultSwitchingProfile_SpoofGuard();
			this.addSwitchingProfile(switchingProfile);
			
			if(!this.isExist(spoofGuardName)){
				assert false: "Failed to add SwitchingProfile_spoofGuard";
			}
		}
		
		if(!this.isExist(qosName)){
			switchingProfile = this.getDefaultSwitchingProfile_QoS();
			this.addSwitchingProfile(switchingProfile);
			
			if(!this.isExist(qosName)){
				assert false: "Failed to add SwitchingProfile_qos";
			}
		}
		
		if(!this.isExist(switchSecurityName)){
			switchingProfile = this.getDefaultSwitchingProfile_SwitchSecurity();
			this.addSwitchingProfile(switchingProfile);
			
			if(!this.isExist(switchSecurityName)){
				assert false: "Failed to add SwitchingProfile_switchSecurity";
			}
		}
	}
	
	public void cleanSwitchingProfiles(){
		if(this.isExist(ipDiscoveryName)){
			this.deleteSwitchingProfile(ipDiscoveryName);
			
			if(this.isExist(ipDiscoveryName)){
				assert false: "Failed to delete SwitchingProfile_ipDiscovery";
			}
		}
		
		if(this.isExist(protMirroringName)){
			this.deleteSwitchingProfile(protMirroringName);
			
			if(this.isExist(ipDiscoveryName)){
				assert false: "Failed to delete SwitchingProfile_protMirroring";
			}
		}
		
		if(this.isExist(spoofGuardName)){
			this.deleteSwitchingProfile(spoofGuardName);
			
			if(this.isExist(spoofGuardName)){
				assert false: "Failed to delete SwitchingProfile_spoofGuard";
			}
		}
		
		if(this.isExist(qosName)){
			this.deleteSwitchingProfile(qosName);
			
			if(this.isExist(qosName)){
				assert false: "Failed to delete SwitchingProfile_qos";
			}
		}
		
		if(this.isExist(switchSecurityName)){
			this.deleteSwitchingProfile(switchSecurityName);
			
			if(this.isExist(switchSecurityName)){
				assert false: "Failed to delete SwitchingProfile_switchSecurity";
			}
		}
	}
}
