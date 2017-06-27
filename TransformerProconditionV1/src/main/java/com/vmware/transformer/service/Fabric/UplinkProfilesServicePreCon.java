package com.vmware.transformer.service.Fabric;

import java.util.ArrayList;

import com.vmware.transformer.model.Fabric.Lags;
import com.vmware.transformer.model.Fabric.Teaming;
import com.vmware.transformer.model.Fabric.Uplink;
import com.vmware.transformer.model.Fabric.UplinkProfile;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class UplinkProfilesServicePreCon {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	public String display_name;
	public String second_displayName;
	//Set default uplink name
	public String activeUplinkName;
	public String standbyUplinkName;
	public String lagsName;
	
	public UplinkProfilesServicePreCon() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/host-switch-profiles/";
	
	}


	public UplinkProfile getUplinkProfile_FailoverType_NoStandbyUplink_NoLags(String uplinkProfileDisplayName, String activeUplinkName){
		Uplink active01 = new Uplink("PNIC", activeUplinkName);
		
		ArrayList<Uplink> active_list = new ArrayList<Uplink>();
		active_list.add(active01);
		ArrayList<Uplink> standby_list = new ArrayList<Uplink>();

		Teaming teaming = new Teaming(active_list, standby_list, "FAILOVER_ORDER");

		ArrayList<Lags> lags_list = new ArrayList<Lags>();

		UplinkProfile uplinkProfile = new UplinkProfile(uplinkProfileDisplayName, uplinkProfileDisplayName, teaming, lags_list, "5", "1600", "UplinkHostSwitchProfile");
		
		return uplinkProfile;
	}
	
	public UplinkProfile getUplinkProfile_FailoverType_NoStandbyUplink(String uplinkProfileDisplayName, String activeUplinkName){
		Uplink active01 = new Uplink("PNIC", activeUplinkName);
		
		ArrayList<Uplink> active_list = new ArrayList<Uplink>();
		active_list.add(active01);
		ArrayList<Uplink> standby_list = new ArrayList<Uplink>();

		Teaming teaming = new Teaming(active_list, standby_list, "FAILOVER_ORDER");

		ArrayList<Lags> lags_list = new ArrayList<Lags>();

		UplinkProfile uplinkProfile = new UplinkProfile(uplinkProfileDisplayName, uplinkProfileDisplayName, teaming, lags_list, "5", "1600", "UplinkHostSwitchProfile");
		
		return uplinkProfile;
	}
	
	
	
	public String checkNameLength(String name){
		String finalName = "";
		if(name.length() >= 28){
			finalName = name.substring(0,13);
		}else{
			finalName = name;
		}
		return finalName;
		
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
	
}
