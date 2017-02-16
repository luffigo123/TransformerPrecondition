package com.vmware.transformer.service.Inventory;

import java.util.ArrayList;

import com.vmware.transformer.model.Inventory.MacSets;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.GetInputString;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class MacSetsService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	//set default IPSets dsplay_name
	//public String display_name = "MacSets001" + TestData.NativeString;
	
	public String display_name;
	public String macSet_Address = "";
	
	public MacSetsService() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/mac-sets/";
		macSet_Address = DefaultEnvironment.macSet_Address;
		
		display_name = GetInputString.getInputString();
	}
	
	/**
	 * get default IPSets instance
	 * @return
	 */
	public MacSets getDefaultMacSets(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("00:11:22:33:44:55");
		MacSets macSets = new MacSets(display_name,display_name, list, "MACSet");
		return macSets;
	}
	


	public String getDefaultMacSetId(){
		MacSets macSets = this.getDefaultMacSets();
		String defualtMacSetName = this.display_name;
		if(!this.isExist(defualtMacSetName)){
			this.addMacSets(macSets);
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String macSetId = this.getObjectId(defualtMacSetName);
		return macSetId;
	}
	
	/**
	 * Add an MacSets
	 * @param MacSets
	 */
	public void addMacSets(MacSets macSets){
		service.addObject(macSets, url);
	}
	
	/**
	 * modify the specific MacSets
	 * @param orgMacSetsName
	 * @param newMacSets
	 */
	public void modifyMacSets(String orgMacSetsName, MacSets newMacSets){

		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgMacSetsName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		//Create the IPSets which you wanted change to.
		MacSets finalMacSets = newMacSets;
		finalMacSets.set_revision(_revision);
		
		//generate the edit url
		String id = this.getObjectId(orgMacSetsName);
		String modifyUrl = url + id;
		service.modifyObject(finalMacSets, modifyUrl);
	}
	
	/**
	 * delete specific MacSets
	 * @param macSetsName
	 */
	public void deleteMacSets(String macSetsName){
		String id = this.getObjectId(macSetsName);
		String deleteUrl = url + id + "/?force=true";
		service.deleteObject(deleteUrl);
	}
	
	
	public boolean isExist(String macSetsName){
		VerifyUtils verifyUtils = new VerifyUtils();
		String jsonString = service.queryObject(url);
		String targetPropertyName = "display_name";
		boolean b = verifyUtils.isTargetExist(jsonString, targetPropertyName, macSetsName);
		return b;
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
	
	public void setup_defaultMacSet(){
		if(!this.isExist(this.display_name)){
			this.addMacSets(this.getDefaultMacSets());
		}
		
		if(!this.isExist(display_name)){
			assert false: "Failed to add MACSet";
		}
	}
	
	public void cleanup_defaultMacSet(){
		if(this.isExist(this.display_name)){
			this.deleteMacSets(this.display_name);
		}
		
		if(this.isExist(display_name)){
			assert false: "Failed to delete MACSet";
		}
	}
	
}
