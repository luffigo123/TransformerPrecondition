package com.vmware.transformer.service.DHCP;

import java.util.ArrayList;

import com.vmware.transformer.model.DHCP.DHCPRelayProfile;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class DHCPRelayProfileServicePreCon {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
//	public String display_name = "";
//	public String second_displayName = "";
	
	public DHCPRelayProfileServicePreCon() 
	{
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/service-profiles/";
		
//		display_name = "DHCPRelayProfile001" + TestData.NativeString;
//		second_displayName = "DHCPRelayProfile002" + TestData.NativeString;
		
//		display_name = GetInputString.getInputString();
//		second_displayName = "DHCPProfile002" + GetInputString.getInputString();
	}

	
	/**
	 * get specific objectId
	 * @param displayName
	 * @return
	 */
	public String getObjectId(String displayName)
	{
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = displayName;
		String targetPropertyName = "id";
		String tzid = jsonUtils.getPropertyValue(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName);
		return tzid;
	}
	
	/**
	 * Add a DHCPProfile
	 * @param dhcpProfile
	 */
	public void addDHCPRelayProfile(DHCPRelayProfile dhcpRelayProfile)
	{
		service.addObject(dhcpRelayProfile, url);
	}
	
	/**
	 * 
	 * @param orgDHCPRelayProfileName
	 * @param newDHCPRelayProfile
	 */
	public void modifyDHCPRelayProfile(String orgDHCPRelayProfileName, DHCPRelayProfile newDHCPRelayProfile)
	{
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgDHCPRelayProfileName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		//Set new DHCPProfile instance
		DHCPRelayProfile dhcpProfile = newDHCPRelayProfile;
		dhcpProfile.set_revision(_revision);
		
		//generate the edit url
		String tzid = this.getObjectId(orgDHCPRelayProfileName);
		String modifyUrl = url + tzid;
		service.modifyObject(dhcpProfile, modifyUrl);
	}
	
	/**
	 * 
	 * @param displayName
	 */
	public void deleteDHCPRelayProfile(String displayName)
	{
		String tzid = this.getObjectId(displayName);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	/**
	 * 
	 * @param displayName
	 * @return
	 */
	public String queryDHCPRelayProfile(String displayName)
	{
		String tzid = this.getObjectId(displayName);
		String queryUrl = url + tzid;
		return service.queryObject(queryUrl);
	}
	
	/**
	 * check the specific object whether exist by name
	 * @param displayName
	 * @return
	 */
	public boolean isExist(String displayName)
	{
		VerifyUtils verifyUtils = new VerifyUtils();
		String jsonString = service.queryObject(url);
		String targetPropertyName = "display_name";
		boolean b = verifyUtils.isTargetExist(jsonString, targetPropertyName, displayName);
		return b;
	}
	
	public DHCPRelayProfile getDHCPRelayProfile(String dhcpRelayProfileName, String dhcpRelayServerIP){
//		String dhcpRelayServerIP = DefaultEnvironment.DHCPRelayServerIP;
		ArrayList<String> serverIPList = new ArrayList<String>();
		serverIPList.add(dhcpRelayServerIP);
		DHCPRelayProfile dhcpRelayProfile = new DHCPRelayProfile(dhcpRelayProfileName, dhcpRelayProfileName, serverIPList, "DhcpRelayProfile");
		return dhcpRelayProfile;
	}
	

}
