package com.vmware.transformer.service.Routing;

import java.util.ArrayList;

import com.vmware.transformer.model.Routing.IPPrefixList;
import com.vmware.transformer.model.Routing.Prefixe;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.TestData;
import com.vmware.transformer.utils.VerifyUtils;

public class RoutingIPPrefixListService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;

	public String display_name = "IPPrefixList001" + TestData.NativeString;
	public String tier = "";
	
	public RoutingService routingService = null;
	public String logicalRouterId = "";
	public String logiclaRouter_DisplayName = "";

	/**
	 * 
	 * @param tierType		Tier0, Tier1
	 */
	public RoutingIPPrefixListService(String tierType) {
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
		
		url = "https://" + vmsIP + "/api/v1/logical-routers/" + logicalRouterId + "/routing/ip-prefix-lists/";
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
	
	public void addIPPrefixList(IPPrefixList ipPrefixList){
		service.addObject(ipPrefixList, url);
	}
	
	/**
	 * 
	 * @param orgIPPrefixList
	 * @param newIPPrefixList
	 */
	public void modifyIPPrefixList(String orgIPPrefixListName, IPPrefixList newIPPrefixList){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgIPPrefixListName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision_value = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		newIPPrefixList.set_revision(_revision_value);
		IPPrefixList finalObject = newIPPrefixList;
		
		//generate the edit url
		String tzid = this.getObjectId(orgIPPrefixListName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);
	}
	
	/**
	 * 
	 * @param displayName
	 */
	public void deleteIPPrefixList(String displayName){
		String tzid = this.getObjectId(displayName);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	/**
	 * 
	 * @param displayName
	 * @return
	 */
	public String querySpecificIPPrefixList(String displayName){
		String id = this.getObjectId(displayName);
		String queryUrl = url + id;
		return service.queryObject(queryUrl);
	}
	
	public String queryAllInfo(){
		return service.queryObject(url);
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
	
	public IPPrefixList getDefaultIPPrefixList(){
		ArrayList<Prefixe> prefixesList = new ArrayList<Prefixe>();
		String network = DefaultEnvironment.ipPrefixList_Network;
		String action = DefaultEnvironment.ipPrefixList_action;
		String ge = DefaultEnvironment.ipPrefixList_ge;
		String le = DefaultEnvironment.ipPrefixList_le;
		Prefixe prefixe01 = new Prefixe(network, action, ge, le);
		prefixesList.add(prefixe01);
		
		IPPrefixList ipPrefixList = new IPPrefixList(this.display_name, prefixesList);
		return ipPrefixList;
	}
	
	public void setPrecondition(String tierType){
		this.routingService.setupDefault_LogicalRouter(tierType);
	}
	
	public void cleanupPrecondition(String tierType){
		this.routingService.clean_Default_LogicalRouter(tierType);
		this.routingService.cleanPrecondition();
	}
	
	public void setup_defaultIPPrefixList(){
		if(!this.isExist(this.display_name)){
			this.addIPPrefixList(this.getDefaultIPPrefixList());
		}
	}
	
	public void cleanup_defaultIPPrefixList(){
		if(this.isExist(this.display_name)){
			this.deleteIPPrefixList(this.display_name);
		}
//		this.cleanupPrecondition(this.tier);
	}
}
