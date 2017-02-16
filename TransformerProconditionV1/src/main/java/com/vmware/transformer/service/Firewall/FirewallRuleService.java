package com.vmware.transformer.service.Firewall;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.vmware.transformer.model.Firewall.ApplyTo;
import com.vmware.transformer.model.Firewall.FirewallRule;
import com.vmware.transformer.model.Firewall.Services;
import com.vmware.transformer.model.Firewall.SourceDestination;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.service.Inventory.IPSetsService;
import com.vmware.transformer.service.Inventory.MacSetsService;
import com.vmware.transformer.service.Inventory.NSGroupService;
import com.vmware.transformer.service.Inventory.NSServicesService;
import com.vmware.transformer.service.Switching.LogicalPortService;
import com.vmware.transformer.service.Switching.LogicalSwitchService;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.Log4jInstance;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.TestData;
import com.vmware.transformer.utils.VerifyUtils;

public class FirewallRuleService {
	public String vmsIP = null;
	public Service service = null;
	public String url = null;
	
	public NSGroupService nsGroupService;
	public IPSetsService ipSetsService;
	public MacSetsService macSetsService;
	public LogicalSwitchService logicalSwitch_Service;
	public LogicalPortService logicalPort_Service;
	public NSServicesService nss;
	
	public SectionService sectionService;
	
	//set default NSGroup dsplay_name
	public String display_name = "FirewallRule001" + TestData.NativeString;
	public Logger logger = Log4jInstance.getLoggerInstance();
	
	public FirewallRuleService(String layer) {
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;

		logger.info("Step 1: Check the default Section whether exist, if not add it.");
		sectionService = new SectionService();
		this.setupPrecondition(layer);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String defaultSectionId = sectionService.getObjectId(this.sectionService.display_name, layer);
		
		url = "https://" + vmsIP + "/api/v1/firewall/sections/" + defaultSectionId + "/rules/";
		
		nsGroupService = new NSGroupService();
		ipSetsService = new IPSetsService();
		logicalSwitch_Service = new LogicalSwitchService();
		logicalPort_Service = new LogicalPortService();
		macSetsService = new MacSetsService();
		nss = new NSServicesService();	
	}
	
	/**
	 * Get default Section instance
	 * @param layer		LAYER3 | LAYER2
	 * @return
	 */
	public FirewallRule getDefaultFirewallRule(String layer){
		
//		logger.info("Step 1: Check the default Section whether exist, if not add it.");
//		Section defaultSection = sectionService.getDefaultSection(layer);
//		String defaultSectionName = defaultSection.getDisplay_name();
//		if(!sectionService.isExist(defaultSectionName, layer)){
//			sectionService.addSection(defaultSection);
//		}

		logger.info("Create the firewallRuleService instance ");
		FirewallRule firewallRule = null;
		
		logger.info("Step 3: Generate the Source and Destination object list");
		ArrayList<SourceDestination> sourceList = new ArrayList<SourceDestination>();
		ArrayList<SourceDestination> destinationList = new ArrayList<SourceDestination>();
		
//		firewallRule = new FirewallRule(display_name, "false", "false", "false", "IPV4_IPV6", "ALLOW", "false", "IN_OUT", sourceList, destinationList);
		
		firewallRule = new FirewallRule(display_name, "false", "nots", "false", "false","IPV4_IPV6", "ALLOW", "false", "IN_OUT", "ruletag", sourceList, destinationList, null, null);
		
		return firewallRule;
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
	 * Add a FirewallRule
	 * @param firewallRule
	 */
	public void addFirewallRule(FirewallRule firewallRule){
		service.addObject(firewallRule, url);
	}
	
	
	/**
	 * modify the specific FirewallRule
	 * @param orgFirewallRuleName
	 * @param newFirewallRule
	 */
	public void modifyFirewallRule(String orgFirewallRuleName, String firewallId,FirewallRule newFirewallRule){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgFirewallRuleName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision= String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		//Set new Section instance
		FirewallRule finalFirewallRule = newFirewallRule;
		finalFirewallRule.set_revision(_revision);
		
		//generate the edit url

		String modifyUrl = url + firewallId;
		service.modifyObject(finalFirewallRule, modifyUrl);

	}
	
	/**
	 * delete specific FirewallRule
	 * @param displayName
	 */
	public void deleteFirewallRule(String displayName){
		String tzid = this.getObjectId(displayName);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	
	/**
	 * query the specific FirewallRule info
	 * @param displayName
	 * @return
	 */
	public String queryFirewallRule(String displayName){
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
	 * get the applyto list
	 * @return
	 */
	public ArrayList<ApplyTo> getApplyToList(){		
		ArrayList<ApplyTo> list = new ArrayList<ApplyTo>();
		
		logger.info("Step 1-1: Check the default NSGroup whether exist, if not add it.");
		String defaultNSGroupname = nsGroupService.display_name;
		String defaultNSGroupId = nsGroupService.getDefaultNSGroupId();	
		logger.info("Step 1-2: Generate the AppyTo object(NSGroup) list, add it to list.");		
		ApplyTo applyTo_NSGroup = new ApplyTo(defaultNSGroupname, "true", "NSGroup",defaultNSGroupId);
		list.add(applyTo_NSGroup);
		
		
		logger.info("Step 2-1: Get the default LogicalSwitch, if it's not exist, add it.");		
		String defaultLogicalSwitch_Name = logicalSwitch_Service.display_name;
		String defaultLogicalSwitchId = logicalSwitch_Service.getDefaultLogicalSwitchId();
//		String defaultLogicalSwitch_Name = logicalSwitch_Service.display_name_env;
//		String defaultLogicalSwitchId = logicalSwitch_Service.getLogicalSwitchId_ForPreEnv();
		
		
		logger.info("Step 2-2: Generate the AppyTo object(LogicalSwitch) list, add it to list.");
		ApplyTo applyTo_logicalSwitch = new ApplyTo(defaultLogicalSwitch_Name, "true", "LogicalSwitch", defaultLogicalSwitchId);
		list.add(applyTo_logicalSwitch);
		
		
		logger.info("Step 3-1: Get the default LogicalPort, if it's not exist, add it.");
		String defaultLogicalPort_Name = logicalPort_Service.display_name;
		String defaultLogicalPortId = logicalPort_Service.getDefaultLogicalPortId();
		
		logger.info("Step 3-2: Generate the AppyTo object(LogicalPort) list, add it to list.");
		ApplyTo applyTo_LogicalPort = new ApplyTo(defaultLogicalPort_Name, "true", "LogicalPort", defaultLogicalPortId);
		list.add(applyTo_LogicalPort);
		
		return list;
	}
	
	/**
	 * get the source and destination items list Note: For General FirewallRule
	 * @return
	 */
	public ArrayList<SourceDestination> getSourceDestinationList(){
		ArrayList<SourceDestination> sourceDestinationList = new ArrayList<SourceDestination>();
		
		logger.info("Step 1: Add IPAddress to list!");
		SourceDestination ipAddress = new SourceDestination("192.168.1.2","true","IPv4Address","192.168.1.2");
		sourceDestinationList.add(ipAddress);
		
		logger.info("Step 2: Add NSGroup to list!");
		String defaultNSGroupname = nsGroupService.display_name;
		String defaultNSGroupId = nsGroupService.getDefaultNSGroupId();
		SourceDestination nsGroup = new SourceDestination(defaultNSGroupname,"true","NSGroup",defaultNSGroupId);
		sourceDestinationList.add(nsGroup);
		
		logger.info("Step 3: Add IPSet to list!");
		String defaultIPSetName = ipSetsService.display_name;
		String defaultIPSetId = ipSetsService.getDefaultIPSetId();
		SourceDestination ipset = new SourceDestination(defaultIPSetName,"true","IPSet",defaultIPSetId);
		sourceDestinationList.add(ipset);
		
		logger.info("Step 4: Get the default LogicalSwitch to list!");
		String defaultLogicalSwitch_Name = logicalSwitch_Service.display_name;
		String defaultLogicalSwitchId = logicalSwitch_Service.getDefaultLogicalSwitchId();
//		String defaultLogicalSwitch_Name = logicalSwitch_Service.display_name_env;
//		String defaultLogicalSwitchId = logicalSwitch_Service.getLogicalSwitchId_ForPreEnv();
		
		SourceDestination logicalSiwtch = new SourceDestination(defaultLogicalSwitch_Name,"true","LogicalSwitch",defaultLogicalSwitchId);
		sourceDestinationList.add(logicalSiwtch);
		
		logger.info("Step 5: Get the default LogicalPort to list!");
		String defaultLogicalPort_Name = logicalPort_Service.display_name;
		String defaultLogicalPortId = logicalPort_Service.getDefaultLogicalPortId();
		SourceDestination logicalPort = new SourceDestination(defaultLogicalPort_Name,"true","LogicalPort",defaultLogicalPortId);
		sourceDestinationList.add(logicalPort);
			
		return sourceDestinationList;
	}
	
	/**
	 * get the source and destination items list Note: For General FirewallRule
	 * @return
	 */
	public ArrayList<SourceDestination> getSourceDestinationList_ForEthernetFirewallRule(){
		ArrayList<SourceDestination> sourceDestinationList = new ArrayList<SourceDestination>();
		
		logger.info("Step 1: Mac IPAddress to list!");
		SourceDestination macAddress = new SourceDestination("11:22:33:44:55:66","true","MACAddress","11:22:33:44:55:66");
		sourceDestinationList.add(macAddress);
		
		logger.info("Step 2: Add NSGroup to list!");
		String defaultNSGroupname = nsGroupService.display_name;
		String defaultNSGroupId = nsGroupService.getDefaultNSGroupId();
		SourceDestination nsGroup = new SourceDestination(defaultNSGroupname,"true","NSGroup",defaultNSGroupId);
		sourceDestinationList.add(nsGroup);
		
		logger.info("Step 3: Add MactSet to list!");
		String defaultMacSetName = macSetsService.display_name;
//		String defaultMacSetId = macSetsService.getMacSetsId(defaultMacSetName);
		String defaultMacSetId = macSetsService.getDefaultMacSetId();
		SourceDestination macset = new SourceDestination(defaultMacSetName,"true","MACSet",defaultMacSetId);
		sourceDestinationList.add(macset);
		
		logger.info("Step 4: Get the default LogicalSwitch to list!");
		String defaultLogicalSwitch_Name = logicalSwitch_Service.display_name;
		String defaultLogicalSwitchId = logicalSwitch_Service.getDefaultLogicalSwitchId();
//		String defaultLogicalSwitch_Name = logicalSwitch_Service.display_name_env;
//		String defaultLogicalSwitchId = logicalSwitch_Service.getLogicalSwitchId_ForPreEnv();
		SourceDestination logicalSiwtch = new SourceDestination(defaultLogicalSwitch_Name,"true","LogicalSwitch",defaultLogicalSwitchId);
		sourceDestinationList.add(logicalSiwtch);
		
		logger.info("Step 5: Get the default LogicalPort to list!");
		String defaultLogicalPort_Name = logicalPort_Service.display_name;
		String defaultLogicalPortId = logicalPort_Service.getDefaultLogicalPortId();
		SourceDestination logicalPort = new SourceDestination(defaultLogicalPort_Name,"true","LogicalPort",defaultLogicalPortId);
		sourceDestinationList.add(logicalPort);
			
		return sourceDestinationList;
	}
	
	/**
	 * get the Service list, include HTTP and HTTPS
	 * @return
	 */
	public ArrayList<Services> getServiceList(){
		ArrayList<Services> servicesList = new ArrayList<Services>();
		
		//add NSServices to list");
		nss = new NSServicesService();
		String httpServiceID = nss.getObjectId("HTTP");
		String httpsServiceID = nss.getObjectId("HTTPS");
		Services httpService = new Services("HTTP", "true", "NSService", httpServiceID);
		Services httpsService = new Services("HTTPS", "true", "NSService", httpsServiceID);
		
		servicesList.add(httpService);
		servicesList.add(httpsService);
		return servicesList;
	}
	
	/**
	 * get the Service list, include default NSService_EtherType
	 * @return
	 */
	public ArrayList<Services> getServiceList_ForEthernetFirewallRule(){
		ArrayList<Services> servicesList = new ArrayList<Services>();
		
		nss = new NSServicesService();
		String nsServiceId = nss.getNSServiceId("EtherType", nss.defaultEtherTypeName);
		Services nsService = new Services(nss.defaultEtherTypeName, "true", "NSService", nsServiceId);
		
		servicesList.add(nsService);
		
		return servicesList;
	}
	
	/**
	 * 
	 *	Check the given itemValue whether equals query value from the specific firewall rule
	 *
	 * @param firewallRuleName  
	 * @param itemName			the specific itemName(key) that you want query the value
	 * @param itemValue			the given itemValue you offered
	 * @param vauleType			specific the itemValue's type in json info, such as:  boolean | int | string  
	 * @return
	 */
	public boolean verifySpecifyItemValue(String firewallRuleName, String itemName,String itemValue, String vauleType){
		boolean result = false;
		JsonUtils ju = new JsonUtils();
		String firewallRuleInfo = this.queryFirewallRule(firewallRuleName);
		String queryItemValue = ju.getJsonItemValue(firewallRuleInfo, itemName,vauleType);
		if(!queryItemValue.isEmpty() && !itemValue.isEmpty()){
			if(queryItemValue.equals(itemValue)){
				result = true;
			}
		}else{
			logger.error("The queryItemValue or itemValue is null");
		}
		return result;
	}
	
	/**
	 * get the specific item's values
	 * @param firewallRuleName
	 * @param itemName
	 * @return
	 */
	public ArrayList<String> getSpecificItemValues(String firewallRuleName, String jsonArrayName,String itemName){
		ArrayList<String> list = new ArrayList<String>();
		JsonUtils ju = new JsonUtils();
		String firewallRuleInfo = this.queryFirewallRule(firewallRuleName);
		list = ju.getSpecificPropertyValues_givenJsonArrayName(firewallRuleInfo, jsonArrayName, itemName);
		return list;
	}
	
	/**
	 *  check the givenValue whether exist in the query info.
	 * @param firewallRuleName
	 * @param itemName
	 * @param checkValue
	 * @return
	 */
	public boolean isExist(String firewallRuleName, String itemName, String givenValue, String jsonArrayName){
		boolean result = false;
		ArrayList<String> list = this.getSpecificItemValues(firewallRuleName,jsonArrayName, itemName);
		for(String itemValue : list){
			if(itemValue.equals(givenValue.trim())){
				result = true;
				break;
			}
		}
		return result;
	}
	
	public void setupPrecondition(String layer){
		this.sectionService.setupSection(layer);
	}
	
	public void cleanupPrecondition(String layer){
		this.sectionService.cleanupSection(layer);
		//String nsServiceId = nss.getNSServiceId("EtherType", nss.defaultEtherTypeName);
		if(this.nss.isExist02(this.nss.defaultEtherTypeName)){
			this.nss.deleteNSGroup(this.nss.defaultEtherTypeName);	
		}
		
	}
	
	public void setupFirewall(String layer){
//		this.setupPrecondition(layer);
		if(!this.isExist(this.display_name)){
			this.addFirewallRule(this.getDefaultFirewallRule(layer));
		}
	}
	
	public void cleanupFirewall(String layer){
		if(this.isExist(this.display_name)){
			this.deleteFirewallRule(this.display_name);
		}
		this.cleanupPrecondition(layer);
	}
	
//	public void clearLayer3_PreconditionEnv(){		
//		nsGroupService.deleteNSGroup(nsGroupService.display_name);
//		ipSetsService.deleteTIPSets(ipSetsService.display_name);
//		logicalPort_Service.deleteLogicalPortWithAttach(logicalPort_Service.display_name);
//		logicalSwitch_Service.deleteLogicalSwitch(logicalSwitch_Service.display_name_env);
//	}
	
//	public void cleanPreconditionEnv(){	
//		String nsGroupServiceName = nsGroupService.display_name;
//		if(nsGroupService.isExist(nsGroupServiceName)){
//			nsGroupService.deleteNSGroup(nsGroupService.display_name);
//		}
//		
//		String macSetName = macSetsService.display_name;
//		if(macSetsService.isExist(macSetName)){
//			macSetsService.deleteMacSets(macSetsService.display_name);
//		}
//		
//		String ipSetName = ipSetsService.display_name;
//		if(ipSetsService.isExist(ipSetName)){
//			ipSetsService.deleteTIPSets(ipSetName);
//		}
//		
//		String nsServiceName = nss.defaultEtherTypeName;
//		if(nss.isExist02(nsServiceName)){
//			nss.deleteNSGroup(nss.defaultEtherTypeName);
//		}
//		
//		String lpName = logicalPort_Service.display_name;
//		if(logicalPort_Service.isExist(lpName)){
//			logicalPort_Service.deleteLogicalPortWithAttach(lpName);
//		}
//		
//		String lsName = logicalSwitch_Service.display_name_env;
//		if(logicalSwitch_Service.isExist(lsName)){
//			logicalSwitch_Service.deleteLogicalSwitch(lsName);
//		}
//		
//	}
	
}
