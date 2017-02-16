package com.vmware.transformer.service.Inventory;

import java.util.ArrayList;

import com.vmware.transformer.model.Inventory.NSServiceALG;
import com.vmware.transformer.model.Inventory.NSServiceALG.ALG;
import com.vmware.transformer.model.Inventory.NSServiceEtherType;
import com.vmware.transformer.model.Inventory.NSServiceEtherType.EtherType;
import com.vmware.transformer.model.Inventory.NSServiceICMPType;
import com.vmware.transformer.model.Inventory.NSServiceICMPType.ICMPType;
import com.vmware.transformer.model.Inventory.NSServiceIGMPType;
import com.vmware.transformer.model.Inventory.NSServiceIGMPType.IGMPType;
import com.vmware.transformer.model.Inventory.NSServiceIPProtocolService;
import com.vmware.transformer.model.Inventory.NSServiceIPProtocolService.IPProtocolNSService;
import com.vmware.transformer.model.Inventory.NSServiceL4PortSet;
import com.vmware.transformer.model.Inventory.NSServiceL4PortSet.L4PortSet;
import com.vmware.transformer.model.Inventory.NSServices;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.GetInputString;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;


public class NSServicesService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	//set default NSGroup dsplay_name
	public String defaultEtherTypeName = "";
	public String defaultIPProName = "";
	public String defaultIGMPName = "";
	public String defaultICMPName = "";
	public String defaultALGName = "";
	public String defaultL4PortSetName = "";

	public NSServicesService() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/ns-services/";
		
//		defaultEtherTypeName = GetInputString.getInputString() + "NSServiceEtherType";
//		defaultIPProName = GetInputString.getInputString() + "NSServiceIPProtocolService";
//		defaultIGMPName = GetInputString.getInputString() + "NSServiceIGMPType";
//		defaultICMPName = GetInputString.getInputString() + "NSServiceICMPType";
//		defaultALGName = GetInputString.getInputString() + "NSServiceALG";
//		defaultL4PortSetName = GetInputString.getInputString() + "NSServiceL4PortSet";
		
		defaultEtherTypeName = GetInputString.getInputString() + "NSServiceEtherType";
		defaultIPProName = GetInputString.getInputString() + "NSServiceIPProtocolService";
		defaultIGMPName = GetInputString.getInputString() + "NSServiceIGMPType";
		defaultICMPName = GetInputString.getInputString() + "NSServiceICMPType";
		defaultALGName = GetInputString.getInputString() + "NSServiceALG";
		defaultL4PortSetName = GetInputString.getInputString() + "NSServiceL4PortSet";
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
	 * Add a NSServices
	 * @param nsServices
	 */
	public void addNSServices(NSServices nsServices){
		service.addObject(nsServices, url);
	}
	
	
	public void modifyNSServiceEtherType(String orgName, NSServiceEtherType newNSServiceEtherType){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		NSServiceEtherType finalNSServiceEtherType = new NSServiceEtherType(newNSServiceEtherType.getDisplay_name(),newNSServiceEtherType.getDescription(),
				newNSServiceEtherType.getNsservice_element(),_revision);
			
		//generate the edit url
		String tzid = this.getObjectId(orgName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalNSServiceEtherType, modifyUrl);
	}
	
	public void modifyNSServiceIPProtocolService(String orgName, NSServiceIPProtocolService newNSServiceIPProtocolService){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		NSServiceIPProtocolService finalObject = new NSServiceIPProtocolService(newNSServiceIPProtocolService.getDisplay_name(), newNSServiceIPProtocolService.getDescription(), 
				newNSServiceIPProtocolService.getNsservice_element(), _revision);
			
		//generate the edit url
		String tzid = this.getObjectId(orgName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);
	}

	public void modifyNSServiceIGMPType(String orgName, NSServiceIGMPType newNSServiceIGMPType){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		NSServiceIGMPType finalObject = new NSServiceIGMPType(newNSServiceIGMPType.getDisplay_name(), newNSServiceIGMPType.getDescription(), 
				newNSServiceIGMPType.getNsservice_element(), _revision);
			
		//generate the edit url
		String tzid = this.getObjectId(orgName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);
	}
	
	public void modifyNSServiceICMPType(String orgName, NSServiceICMPType newNSServiceICMPType){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		NSServiceICMPType finalObject = new NSServiceICMPType(newNSServiceICMPType.getDisplay_name(), newNSServiceICMPType.getDescription(),
				newNSServiceICMPType.getNsservice_element(), _revision);
			
		//generate the edit url
		String tzid = this.getObjectId(orgName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);
	}
	
	public void modifyNSServiceALG(String orgName, NSServiceALG newNSServiceALG){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		NSServiceALG finalObject = new NSServiceALG(newNSServiceALG.getDisplay_name(), newNSServiceALG.getDescription(),
				newNSServiceALG.getNsservice_element(), _revision);
			
		//generate the edit url
		String tzid = this.getObjectId(orgName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);
	}
	
	public void modifyNSServiceL4PortSet(String orgName, NSServiceL4PortSet newNSServiceL4PortSet){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		NSServiceL4PortSet finalObject = new NSServiceL4PortSet(newNSServiceL4PortSet.getDisplay_name(), newNSServiceL4PortSet.getDescription(), 
				newNSServiceL4PortSet.getNsservice_element(), _revision);
			
		//generate the edit url
		String tzid = this.getObjectId(orgName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);
	}
	
	/**
	 * delete specific NSServices
	 * @param displayName
	 */
	public void deleteNSGroup(String displayName){
		String tzid = this.getObjectId(displayName);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
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
	 * check the specific object whether exist by name
	 * @param displayName
	 * @return
	 */
	public boolean isExist02(String displayName){
		boolean flag = false;
		String jsonString = service.queryObject(url);
		if(jsonString.contains(displayName)){
			flag = true;
		}
		return flag;
	}
	
	public String queryNS(){
		String queryInfo = service.queryObject(url);
		return queryInfo;
	}
	
	public String querySpecifyObjInfo(String dispalyName){
		String Id = this.getObjectId(dispalyName);
		String newUrl = this.url + Id;
		String result = service.queryObject(newUrl);
		return result;
	}
	
	public NSServiceEtherType getDefaultNSServiceEtherType(){
		ArrayList<EtherType> etList = NSServiceUtils.getEtherTypeList();
		NSServiceEtherType defaultNSServiceEtherType = new NSServiceEtherType(defaultEtherTypeName, defaultEtherTypeName, etList.get(0));
		return defaultNSServiceEtherType;
	}
	
	public NSServiceIPProtocolService getDefaultIPPro(){
		ArrayList<IPProtocolNSService> ipProList = NSServiceUtils.getIPProtocolNSServiceList();
		NSServiceIPProtocolService defaultIPPro = new NSServiceIPProtocolService(defaultIPProName, defaultIPProName, ipProList.get(0));
		return defaultIPPro;
	}
	
	public NSServiceIGMPType getDefaultNSServiceIGMPType(){
		ArrayList<IGMPType> igmpList = NSServiceUtils.getIGMPTypeList();
		NSServiceIGMPType defaultIGMP = new NSServiceIGMPType(defaultIGMPName,defaultIGMPName,igmpList.get(0));
		return defaultIGMP;
	}
	
	public NSServiceICMPType getDefaultNSServiceICMPType(){
		ArrayList<ICMPType> icmpList = NSServiceUtils.getICMPTypeList();
		NSServiceICMPType defaultICMP = new NSServiceICMPType(defaultICMPName, defaultICMPName, icmpList.get(0));
		return defaultICMP;
	}
	
	public NSServiceALG getDefaultNSServiceALG(){
		ArrayList<ALG> algList = NSServiceUtils.getALGList();
		NSServiceALG defaultALG = new NSServiceALG(defaultALGName, defaultALGName, algList.get(0));
		return defaultALG;
	}
	
	public NSServiceL4PortSet getDefaultNSServiceL4PortSet(){
		ArrayList<L4PortSet> l4PortSetList  = NSServiceUtils.getL4PortSetList();
		NSServiceL4PortSet defualtNSServiceL4PortSet = new NSServiceL4PortSet(defaultL4PortSetName, defaultL4PortSetName, l4PortSetList.get(0));
		return defualtNSServiceL4PortSet;
	}
	
	/**
	 * get the default NSServiceId which you offer the NSService type
	 * @param nsServiceType   EtherType | IPProtocolService | IGMPType | ICMPType | ALG | L4PortSetS
	 * @param objectDisplayName
	 * @return
	 */
	public String getNSServiceId(String nsServiceType, String objectDisplayName){
		NSServices defaultNSServices = null;
		String defaultNSServiceName = "";
		
		switch (nsServiceType){
			case "EtherType":
				defaultNSServices = this.getDefaultNSServiceEtherType();
				defaultNSServiceName = this.defaultEtherTypeName;
				break;
			case "IPProtocolService":
				defaultNSServices = this.getDefaultIPPro();
				defaultNSServiceName = this.defaultIPProName;
				break;
			case "IGMPType":
				defaultNSServices = this.getDefaultNSServiceIGMPType();
				defaultNSServiceName = this.defaultIGMPName;
				break;
			case "ICMPType":
				defaultNSServices = this.getDefaultNSServiceICMPType();
				defaultNSServiceName = this.defaultICMPName;
				break;
			case "ALG":
				defaultNSServices = this.getDefaultNSServiceALG();
				defaultNSServiceName = this.defaultALGName;
				break;
			case "L4PortSet":
				defaultNSServices = this.getDefaultIPPro();
				defaultNSServiceName = this.defaultL4PortSetName;
				break;
			default:
				throw new IllegalArgumentException("Invalid type of the NSService: " + nsServiceType);
		}
		
		if(!this.isExist(defaultNSServiceName)){
			this.addNSServices(defaultNSServices);
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String nsServiceId = this.getObjectId(defaultNSServiceName);
		return nsServiceId;
	}
}
