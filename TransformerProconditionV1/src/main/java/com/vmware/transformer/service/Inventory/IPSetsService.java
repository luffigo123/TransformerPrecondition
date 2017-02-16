package com.vmware.transformer.service.Inventory;

import java.util.ArrayList;

import com.vmware.transformer.model.Inventory.IPSets;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.GetInputString;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class IPSetsService {
	
	private String vmsIP = null;
	private Service service = null;
	private String url = null;

//	public String display_name = "IPSets" + TestData.NativeString;
	
	public String display_name;
			
	public String ipSets_IPAddress = "";
	
	
	public IPSetsService() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/ip-sets/";
		ipSets_IPAddress = DefaultEnvironment.ipSets_IPAddress;
		
		display_name = GetInputString.getInputString();
	}
	
	/**
	 * get default IPSets instance
	 * @return
	 */
	public IPSets getDefaultIPSets(){
		ArrayList<String> list = new ArrayList<String>();
		list.add(this.ipSets_IPAddress);
		IPSets ipsets = new IPSets(display_name,display_name, list, "IPSet");
		return ipsets;
	}
	
	public String getDefaultIPSetId(){
		IPSets ipset = this.getDefaultIPSets();
		String ipSetsName = ipset.getDisplay_name();
		if(!this.isExist(ipSetsName)){
			this.addIPSets(ipset);
		}
		
		String ipSetId = this.getIpSetsId(ipSetsName);
		return ipSetId;
	}
	

	/**
	 * get specific ipSetsId
	 * @param ipSetsName
	 * @return
	 */
	public String getIpSetsId(String ipSetsName){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = ipSetsName;
		String targetPropertyName = "id";
		String tzid = jsonUtils.getPropertyValue(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName);
		return tzid;
	}
	
	/**
	 * Add an IPSets
	 * @param ipSets
	 */
	public void addIPSets(IPSets ipSets){
		service.addObject(ipSets, url);
	}
	
	/**
	 * modify the specific IPSets
	 * @param ipSetsName
	 * @param newIPSets
	 */
	public void modifyIPSets(String orgIpSetsName, IPSets newIPSets){

		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgIpSetsName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		//Create the IPSets which you wanted change to.	
		IPSets finalIPSets = newIPSets;
		newIPSets.set_revision(_revision);
		
		//generate the edit url
		String tzid = this.getIpSetsId(orgIpSetsName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalIPSets, modifyUrl);
	}
	
	/**
	 * delete specific IPSets
	 * @param ipSetsName
	 */
	public void deleteIPSets(String ipSetsName){
		String tzid = this.getIpSetsId(ipSetsName);
		String deleteUrl = url + tzid + "/?force=true";
		service.deleteObject(deleteUrl);
	}
	
	
	/**
	 * query the specific ipSets info
	 * @param ipSetsName
	 * @return
	 */
	public String queryIPSets(String ipSetsName){
		String tzid = this.getIpSetsId(ipSetsName);
		String queryUrl = url + tzid;
		return service.queryObject(queryUrl);
	}
	
	public boolean isExist(String ipSetsName){
		VerifyUtils verifyUtils = new VerifyUtils();
		String jsonString = service.queryObject(url);
		String targetPropertyName = "display_name";
		boolean b = verifyUtils.isTargetExist(jsonString, targetPropertyName, ipSetsName);
		return b;
	}
	
	public void setup_defaultIPSet(){
		if(!this.isExist(this.display_name)){
			this.addIPSets(this.getDefaultIPSets());
		}

		if(!this.isExist(display_name)){
			assert false: "Failed to add IPSet";
		}
	}
	
	public void cleanup_defaultIPset(){
		if(this.isExist(this.display_name)){
			this.deleteIPSets(this.display_name);
		}
		
		if(this.isExist(display_name)){
			assert false: "Failed to delete IPSet";
		}
	
	}

}
