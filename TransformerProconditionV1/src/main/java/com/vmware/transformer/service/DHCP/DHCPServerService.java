package com.vmware.transformer.service.DHCP;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.vmware.transformer.model.DHCP.DHCPService;
import com.vmware.transformer.model.DHCP.IPv4_DHCP_Server;
import com.vmware.transformer.model.DHCP.Option;
import com.vmware.transformer.model.DHCP.Option121;
import com.vmware.transformer.model.DHCP.Other;
import com.vmware.transformer.model.DHCP.StaticRoute;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.GetInputString;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.Log4jInstance;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class DHCPServerService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	public String display_name = "";
//	protected static Log log = Log.getInstance();
	Logger log = Log4jInstance.getLoggerInstance();
	
	public DHCPProfileService dhcpProfileService = null;
	
	public DHCPServerService() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/dhcp/servers/";
		
//		display_name = "DHCPService001" + TestData.NativeString;
		display_name = GetInputString.getInputString();
		
		dhcpProfileService = new DHCPProfileService();
	}
	

	/**
	 * get specific objectId
	 * @param displayName
	 * @return
	 */
	public String getObjectId(String displayName){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = displayName;
		String targetPropertyName = "id";
		String tzid = jsonUtils.getPropertyValue(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName);
		return tzid;
	}
	
	/**
	 * Add a DHCPService
	 * @param dhcpService
	 */
	public void addDHCPService(DHCPService dhcpService){
		service.addObject(dhcpService, url);
	}
	
	
	/**
	 * 
	 * @param orgDHCPServiceName
	 * @param newDHCPService
	 */
	public void modifyDHCPService(String orgDHCPServiceName, DHCPService newDHCPService){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgDHCPServiceName;
		String targetPropertyName ="_revision";
		
		log.info("Get the DHCPService_revision's value");
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		log.info("Set new DHCPProfile instance");
		DHCPService dhcpService = newDHCPService;
		dhcpService.set_revision(_revision);
		
		log.info("Modify the DHCPService!");
		String tzid = this.getObjectId(orgDHCPServiceName);
		String modifyUrl = url + tzid;
		service.modifyObject(dhcpService, modifyUrl);

	}
	
	/**
	 * delete specific DHCPService
	 * @param displayName
	 */
	public void deleteDHCPService(String displayName){
		String tzid = this.getObjectId(displayName);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	
	/**
	 * query the specific DHCPService info
	 * @param displayName
	 * @return
	 */
	public String queryDHCPService(String displayName){
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
		VerifyUtils verifyUtils = new VerifyUtils();
		String jsonString = service.queryObject(url);
		String targetPropertyName = "display_name";
		boolean b = verifyUtils.isTargetExist(jsonString, targetPropertyName, displayName);
		return b;
	}
	
	/**
	 * 
	 * @param domainName
	 * @return
	 */
	public IPv4_DHCP_Server getIPv4_DHCP_Server_Instance(String domainName){
		String gatewayIP =DefaultEnvironment.DHCP_gatewayIP;
		String dhcp_server_ip =DefaultEnvironment.DHCP_dhcpServerIP;
		String dns_nameservers01 = DefaultEnvironment.DHCP_dnsServerIP;
		String staticRouteNetwork = DefaultEnvironment.DHCP_staticRouteNetwork;
		String nextHop = DefaultEnvironment.DHCP_staticRouteNextHop;
		
		ArrayList<String> dns_nameservers_list = new ArrayList<String>();
		dns_nameservers_list.add(dns_nameservers01);
		
		log.info("Classless Static Route Options");
		StaticRoute staticRoute01 = new StaticRoute(staticRouteNetwork, nextHop);
		ArrayList<StaticRoute> staticRouteList = new ArrayList<StaticRoute>();
		staticRouteList.add(staticRoute01);
		Option121 option121 = new Option121(staticRouteList);
		
		log.info("Other Options");
		ArrayList<Other> otherlist = new ArrayList<Other>();
		ArrayList<String> other_value = new ArrayList<String>();
		other_value.add(domainName);
		Other other = null;
//		String [] otherNum = {"211","210","209","119","117","67","66","65","64","59","58","54","47","46","45","44","42",
//				"41","40","35","28","26","2","13","19"};
		String [] otherNum = {"210","209","119","67","66","64","47","40"};
		for(int i =0 ; i < otherNum.length; i++){
			other = new Other(otherNum[i], other_value);
			otherlist.add(other);
		}
		
		Option options = new Option(option121, otherlist); 
		IPv4_DHCP_Server ipv4_dhcp_server = new IPv4_DHCP_Server(domainName, gatewayIP, dhcp_server_ip, dns_nameservers_list,options);	
		return ipv4_dhcp_server;
	}
	
	/**
	 * Get default DHCPService instance
	 * @return
	 */
	public DHCPService getDefaultDHCPService(){
		this.setupPrecondition();
		String defaultDHCPProfileId = this.dhcpProfileService.getObjectId(this.dhcpProfileService.display_name);
		IPv4_DHCP_Server ipv4_dhcp_server = this.getIPv4_DHCP_Server_Instance(this.display_name);
		DHCPService dhcpService = new DHCPService(this.display_name, this.display_name, defaultDHCPProfileId, ipv4_dhcp_server);
		return dhcpService;
	}
	
	public DHCPService getSecondDHCPService(String name){
		this.setupPrecondition_withSecondDHCPProfile();
		String defaultDHCPProfileId = this.dhcpProfileService.getObjectId(this.dhcpProfileService.second_displayName);
		IPv4_DHCP_Server ipv4_dhcp_server = this.getIPv4_DHCP_Server_Instance(name);
		DHCPService dhcpService = new DHCPService(name, name, defaultDHCPProfileId, ipv4_dhcp_server);
		return dhcpService;
	}
	
	public void setupPrecondition(){
		this.dhcpProfileService.setup_Default_DHCPProfile();
	}
	public void cleanupPrecondition(){
		this.dhcpProfileService.clean_Default_DHCPProfile();
	}
	
	public void setupPrecondition_withSecondDHCPProfile(){
		this.dhcpProfileService.setup_Second_DHCPProfile();
	}
	public void cleanupPrecondition_withSecondDHCPProfile(){
		this.dhcpProfileService.clean_Second_DHCPProfile();
	}
	
	/**
	 * setup the default DHCPService (include default DHCPProfile)
	 */
	public void setup_Default_DHCPService(){
		if(!this.isExist(this.display_name)){
			this.addDHCPService(this.getDefaultDHCPService());
		}
		if(!this.isExist(display_name)){
			assert false: "Failed to add DHCPService";
		}
	}
	
	public void clean_Default_DHCPService(){
		if(this.isExist(this.display_name)){
			this.deleteDHCPService(this.display_name);
		}
		if(this.isExist(display_name)){
			assert false: "Failed to delete DHCPService";
		}
		this.cleanupPrecondition();
	}
	
}

