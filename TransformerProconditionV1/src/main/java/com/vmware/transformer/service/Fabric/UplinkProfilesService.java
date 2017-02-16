package com.vmware.transformer.service.Fabric;

import java.util.ArrayList;

import com.vmware.transformer.model.Fabric.Lags;
import com.vmware.transformer.model.Fabric.Teaming;
import com.vmware.transformer.model.Fabric.Uplink;
import com.vmware.transformer.model.Fabric.UplinkProfile;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.GetInputString;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class UplinkProfilesService {
	
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	//set default Logical Switch dsplay_name
//	public String display_name = "UplinkProfile_FailOver" + TestData.NativeString;
//	public String second_displayName = "UplinkProfile_NoLags" + TestData.NativeString;
//	
//	//Set default uplink name
//	public String activeUplinkName = "activeUplink01" + TestData.NativeString;
//	public String standbyUplinkName = "standbyUplink01" + TestData.NativeString;
//	public String lagsName = TestData.NativeString;
	
	//set default Logical Switch dsplay_name
	public String display_name;
	public String second_displayName;
	//Set default uplink name
	public String activeUplinkName;
	public String standbyUplinkName;
	public String lagsName;
	
	public UplinkProfilesService() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/host-switch-profiles/";
		
		lagsName = GetInputString.getInputString();
//		display_name = "UplinkProfile_FailOver" + lagsName;
		display_name = lagsName;
		second_displayName = "UplinkProfile_NoLags" + lagsName;
		
		activeUplinkName = "activeUplink01" + lagsName;
		standbyUplinkName = "standbyUplink01" + lagsName;
		
		
	}

	public UplinkProfile getDefaultUplinkProfile(){
		//log.log("New Uplink Profile instance!");
		
		Uplink active01 = new Uplink("PNIC", activeUplinkName);
		Uplink standby01 = new Uplink("PNIC", standbyUplinkName);
		
		ArrayList<Uplink> active_list = new ArrayList<Uplink>();
		active_list.add(active01);
		ArrayList<Uplink> standby_list = new ArrayList<Uplink>();
		standby_list.add(standby01);
		Teaming teaming = new Teaming(active_list, standby_list, "FAILOVER_ORDER");
		Lags lags = new Lags(lagsName, "ACTIVE", "SRCDESTIPVLAN", "2", "SLOW");
		ArrayList<Lags> lags_list = new ArrayList<Lags>();
		lags_list.add(lags);
		UplinkProfile uplinkProfile = new UplinkProfile(display_name, display_name, teaming, lags_list, "5", "1600", "UplinkHostSwitchProfile");
		
		return uplinkProfile;
	}
	

	public UplinkProfile getUplinkProfile_FailoverType_NoStandbyUplink_NoLags(){
		Uplink active01 = new Uplink("PNIC", activeUplinkName);
		
		ArrayList<Uplink> active_list = new ArrayList<Uplink>();
		active_list.add(active01);
		ArrayList<Uplink> standby_list = new ArrayList<Uplink>();

		Teaming teaming = new Teaming(active_list, standby_list, "FAILOVER_ORDER");

		ArrayList<Lags> lags_list = new ArrayList<Lags>();

//		UplinkProfile uplinkProfile = new UplinkProfile(display_name, display_name, teaming, lags_list, "5", "1600", "UplinkHostSwitchProfile");
		UplinkProfile uplinkProfile = new UplinkProfile(this.second_displayName, this.second_displayName, teaming, lags_list, "5", "1600", "UplinkHostSwitchProfile");
		
		return uplinkProfile;
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
	
	/**
	 * 
	 * @param uplinkProfile
	 */
	public void addUplinkProfile(UplinkProfile uplinkProfile){
		service.addObject(uplinkProfile, url);
	}
	
	/**
	 * 
	 * @param orgUplinkProfileName
	 * @param newUplinkProfile
	 */
	public void modifyUplinkProfile(String orgUplinkProfileName, UplinkProfile newUplinkProfile){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgUplinkProfileName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		UplinkProfile finalObject = newUplinkProfile;
		finalObject.set_revision(_revision);
		
		//generate the edit url
		String tzid = this.getObjectId(orgUplinkProfileName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);
	}
	
	/**
	 * 
	 * @param displayName
	 */
	public void deleteUplinkProfile(String displayName){
		String tzid = this.getObjectId(displayName);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	
	public String queryUplinkProfiles(String displayName){
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

	public ArrayList<String> getLagsList(){
		//SRCDESTIPVLAN | SRCDESTMACIPPORT | SRCDESTMAC | DESTMAC | SRCMAC
		ArrayList<String> lagsList = new ArrayList<String>();
		lagsList.add("SRCDESTIPVLAN");
		lagsList.add("SRCDESTMACIPPORT");
		lagsList.add("SRCDESTMAC");
		lagsList.add("DESTMAC");
		lagsList.add("SRCMAC");
		return lagsList;
	}
	
	
	public void setup_defaultUplinkProfile_FailOverType(){
		if(!this.isExist(this.display_name)){
			this.addUplinkProfile(this.getDefaultUplinkProfile());
		}
		
		if(!this.isExist(display_name)){
			assert false: "Failed to add UplinkProfile_FailOverType!";
		}
	}
	
	public void cleanup_defaultUplinkProfile_FailOverType(){
		if(this.isExist(this.display_name)){
			this.deleteUplinkProfile(this.display_name);
		}
		
		if(this.isExist(display_name)){
			assert false: "Failed to delete UplinkProfile_FailOverType";
		}
	}
	
	public void setup_defaultUplinkProfile_FailOverType_noLags(){
		if(!this.isExist(this.second_displayName)){
			this.addUplinkProfile(this.getUplinkProfile_FailoverType_NoStandbyUplink_NoLags());
		}
		
		if(!this.isExist(second_displayName)){
			assert false: "Failed to add UplinkProfile_FailOverType_noLags!";
		}
	}
	
	public void cleanup_defaultUplinkProfile_FailOverType_noLags(){
		if(this.isExist(this.second_displayName)){
			this.deleteUplinkProfile(this.second_displayName);
		}
		
		if(this.isExist(display_name)){
			assert false: "Failed to delete UplinkProfile_FailOverType_noLags";
		}
	}
	
}
