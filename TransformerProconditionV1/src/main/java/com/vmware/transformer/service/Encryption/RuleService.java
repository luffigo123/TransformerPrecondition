package com.vmware.transformer.service.Encryption;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.vmware.transformer.model.Encryption.Rule;
import com.vmware.transformer.model.Encryption.SourceDestinationService;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.service.Inventory.NSGroupService;
import com.vmware.transformer.service.Inventory.NSServicesService;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.Log4jInstance;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.TestData;
import com.vmware.transformer.utils.VerifyUtils;

public class RuleService {
	public Logger logger = Log4jInstance.getLoggerInstance();
	
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	public String display_name = "Rule001" + TestData.NativeString;
	public SectionService sectionService = null;
	public KeysService keyPolicayService = null;
	public NSGroupService nsGroupService = null;
	public NSServicesService nss = null;;

	public RuleService() {
		this.init();
		this.setupPrecondition();
		String sectionId = sectionService.getObjectId(this.sectionService.display_name);
		url = "https://" + vmsIP + "/api/v1/network-encryption/sections/" + sectionId + "/rules/";

	}
	
	public RuleService(String sectionName) {
		this.init();
		this.setupPrecondition_forDefaultSection();
		String sectionId = sectionService.getObjectId(sectionName);
		url = "https://" + vmsIP + "/api/v1/network-encryption/sections/" + sectionId + "/rules/";
	}
	
	public void init(){
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		keyPolicayService = new KeysService();
		sectionService = new SectionService();
		nsGroupService = new NSGroupService();
		nss = new NSServicesService();
	}
	
	
	/**
	 * 
	 * @param displayName
	 * @return
	 */
	public String getObjectId(String display_name){
		try{
			Thread.sleep(2000);
		}catch(Exception e){
			e.printStackTrace();
		}
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = display_name;
		String targetPropertyName = "id";
		String tzid = jsonUtils.getPropertyValue(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName);
		return tzid;
	}
	

	public void addRule(Rule rule){
		service.addObject(rule, url);
	}

	/**
	 * 
	 * @param orgRuleName
	 * @param newRule
	 */
	public void modifyRule(String orgRuleName, Rule newRule){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = this.queryRuleInfo(orgRuleName);
		String key = "_revision";
		String valueType = "int";
		String _revision  = String.valueOf(jsonUtils.getJsonItemValue(queryInfo, key, valueType));
		Rule finalObject = newRule;
		
		newRule.set_revision(_revision);
		
		String tzid = this.getObjectId(orgRuleName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);
	}
	
	public void deleteRule(String displayName){
		String tzid = this.getObjectId(displayName);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	
	/**
	 * 
	 * @param displayName
	 * @return
	 */
	public String queryRuleInfo(String displayName){
		String tzid = this.getObjectId(displayName);
		String queryUrl = url + tzid;
		return service.queryObject(queryUrl);
	}
	
	public boolean isExist(String displayName){
		try{
			Thread.sleep(2000);
		}catch(Exception e){
			e.printStackTrace();
		}
		VerifyUtils verifyUtils = new VerifyUtils();
		String jsonString = service.queryObject(url);
		String targetPropertyName = "display_name";
		boolean b = verifyUtils.isTargetExist(jsonString, targetPropertyName, displayName);
		return b;
	}
	
	public ArrayList<SourceDestinationService> getSourceDestinationList(){
		ArrayList<SourceDestinationService> list = new ArrayList<SourceDestinationService>();
		
		logger.info("Step 1: Add IPAddress to list!");
		SourceDestinationService ipAddress = new SourceDestinationService("192.168.1.2","true","IPv4Address","192.168.1.2");
		list.add(ipAddress);
		
		logger.info("Step 2: Add NSGroup to list!");
		String defaultNSGroupname = this.nsGroupService.display_name;
		String defaultNSGroupId = this.nsGroupService.getDefaultNSGroupId();
		SourceDestinationService nsGroup = new SourceDestinationService(defaultNSGroupname,"true","NSGroup",defaultNSGroupId);
		list.add(nsGroup);
		
		logger.info("Step 3: Add IPSet to list!");
		String defaultIPSetName = this.nsGroupService.ipsetService.display_name;
		String defaultIPSetId = this.nsGroupService.ipsetService.getDefaultIPSetId();
		SourceDestinationService ipset = new SourceDestinationService(defaultIPSetName,"true","IPSet",defaultIPSetId);
		list.add(ipset);
		
		logger.info("Step 4: Get the default LogicalSwitch to list!");
		String defaultLogicalSwitch_Name = this.nsGroupService.logicalSwitchService.display_name;
		String defaultLogicalSwitchId = this.nsGroupService.logicalSwitchService.getDefaultLogicalSwitchId();
		SourceDestinationService logicalSiwtch = new SourceDestinationService(defaultLogicalSwitch_Name,"true","LogicalSwitch",defaultLogicalSwitchId);
		list.add(logicalSiwtch);
		
		logger.info("Step 5: Get the default LogicalPort to list!");
		String defaultLogicalPort_Name = this.nsGroupService.logicalPortService.display_name;
		String defaultLogicalPortId = this.nsGroupService.logicalPortService.getDefaultLogicalPortId();
		SourceDestinationService logicalPort = new SourceDestinationService(defaultLogicalPort_Name,"true","LogicalPort",defaultLogicalPortId);
		list.add(logicalPort);
		
		return list;
	}
	
	public ArrayList<SourceDestinationService> getServiceList(){
		ArrayList<SourceDestinationService> servicesList = new ArrayList<SourceDestinationService>();
		String httpServiceID = this.nss.getObjectId("HTTP");
		String httpsServiceID = this.nss.getObjectId("HTTPS");
		SourceDestinationService httpService = new SourceDestinationService("HTTP", "true", "NSService", httpServiceID);
		SourceDestinationService httpsService = new SourceDestinationService("HTTPS", "true", "NSService", httpsServiceID);

		servicesList.add(httpService);
		servicesList.add(httpsService);
		return servicesList;
	}
	
	public ArrayList<String> getActionList(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("INTEGRITY_ONLY");
		list.add("ENCRYPTION_AND_INTEGRITY");
		list.add("ALLOW_IN_CLEAR");
		return list;
		
	}
	
	public Rule getDefaultRule(){
		String key_policy_identifier = this.keyPolicayService.getObjectId(this.keyPolicayService.display_name);;
		ArrayList<SourceDestinationService> sources = this.getSourceDestinationList();
		ArrayList<SourceDestinationService> destinations = sources;
		ArrayList<SourceDestinationService> services = this.getServiceList();
		String logged = "true";
		String action = "ENCRYPTION_AND_INTEGRITY";
		String disabled = "false";
		Rule rule = new Rule(this.display_name, key_policy_identifier, this.display_name, sources, destinations, services, logged, action, disabled);
		return rule;
	}
	
	public void setupPrecondition(){
		this.nsGroupService.setup_defaultNSGroup();
		this.keyPolicayService.setup_defaultKeyPolicy();
		this.sectionService.setup_defaultSection();
	}
	
	public void cleanupPrecondition(){
		this.keyPolicayService.cleanup_defaultKeyPolicy();
		this.sectionService.cleanup_defaultSection();
		this.nsGroupService.cleanup_defaultNSGroup();
	}
	
	public void setupPrecondition_forDefaultSection(){
		this.nsGroupService.setup_defaultNSGroup();
		this.keyPolicayService.setup_defaultKeyPolicy();
	}
	
	public void cleanupPrecondition_forDefaultSection(){
		this.keyPolicayService.cleanup_defaultKeyPolicy();
		this.nsGroupService.cleanup_defaultNSGroup();
	}
	
	public void setupDefaultRule(){
		if(!this.isExist(this.display_name)){
			this.addRule(this.getDefaultRule());
		}
	}
	
	public void cleanupDefaultRule(){
		if(this.isExist(this.display_name)){
			this.deleteRule(this.display_name);
		}
	}
}
