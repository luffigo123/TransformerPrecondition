package com.vmware.transformer.service.DHCP;

import com.vmware.transformer.model.DHCP.DHCPRelayServer;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.GetInputString;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class DHCPRelayServerService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	public String display_name = "";
	public DHCPRelayProfileService dhcpRelayProfileService = null;
	
	public DHCPRelayServerService() 
	{
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/services/";
		
//		display_name = "DHCPRelayServer001" + TestData.NativeString;
		display_name = GetInputString.getInputString();
		
		dhcpRelayProfileService = new DHCPRelayProfileService();
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
	public void addDHCPRelayServer(DHCPRelayServer dhcpRelayServer)
	{
		service.addObject(dhcpRelayServer, url);
	}
	
	/**
	 * 
	 * @param orgDHCPRelayProfileName
	 * @param newDHCPRelayProfile
	 */
	public void modifydhcpRelayServer(String orgDHCPRelayServerName, DHCPRelayServer newdhcpRelayServer)
	{
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgDHCPRelayServerName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		//Set new DHCPProfile instance
		DHCPRelayServer finalObject = newdhcpRelayServer;
		finalObject.set_revision(_revision);
		
		//generate the edit url
		String tzid = this.getObjectId(orgDHCPRelayServerName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);
	}
	
	/**
	 * 
	 * @param displayName
	 */
	public void deleteDHCPRelayServer(String displayName)
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
	public String queryDHCPRelayServer(String displayName)
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
	
	public DHCPRelayServer getDefaultDHCPRelayServer(){
		this.setupPrecondition();
		String dhcpRelayProfileID = this.dhcpRelayProfileService.getObjectId(this.dhcpRelayProfileService.display_name);
		DHCPRelayServer dhcpRelayServer = new DHCPRelayServer(this.display_name, this.display_name, dhcpRelayProfileID, "DhcpRelayService");
		return dhcpRelayServer;
	}
	
	public void setupPrecondition(){
		this.dhcpRelayProfileService.setup_DHCPRelayProfile();
	}
	
	public void cleanupPrecondition(){
		this.dhcpRelayProfileService.cleanup_DHCPRelayprofile();
	}
	
	public void setup_defaultDHCPRelayServer(){
		if(!this.isExist(this.display_name)){
			this.addDHCPRelayServer(this.getDefaultDHCPRelayServer());
		}
		if(!this.isExist(display_name)){
			assert false: "Failed to add DHCPRelayService";
		}
	}
	
	public void cleanup_defaultDHCPRelayServer(){
		if(this.isExist(this.display_name)){
			this.deleteDHCPRelayServer(this.display_name);
		}
		if(this.isExist(display_name)){
			assert false: "Failed to delete DHCPRelayService";
		}
		this.cleanupPrecondition();
	}
	
}
