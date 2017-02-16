package com.vmware.transformer.service.Fabric;

import com.vmware.transformer.model.Fabric.TransportZone;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.GetInputString;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class TransportZoneService{
	
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	//set default TransportZone dsplay_name
//	public String display_name = "TZ001" + TestData.NativeString;
	
	public String display_name;
	public String hostswitchName;
	
	public TransportZoneService() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/transport-zones/";
		
		display_name = GetInputString.getInputString();
		hostswitchName = display_name;
	}
	
	/**
	 * get default TransportZone instance -- Type: overlay
	 * @return
	 */
	public TransportZone getDefaultTransportZone(){
		TransportZone tz = new TransportZone(display_name,hostswitchName,display_name,"OVERLAY");
		return tz;
	}
	
	/**
	 * 
	 * @param displayName
	 * @return
	 */
	public String getTransportZoneId(String displayName){
		JsonUtils jsonUtils = new JsonUtils();
		String info = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = displayName;
		String targetPropertyName = "id";
		String id = jsonUtils.getPropertyValue(info, promptPropertyName, promptPropertyValue, targetPropertyName);
		return id;
	}
	
	/**
	 * get the default transprotZoneId
	 * @return
	 */
	public String getDefaultTransportZoneId(){
		String tzId = "";
		TransportZone tz = this.getDefaultTransportZone();
		if(!this.isExist(tz.getDisplay_name())){
			this.addTransportZone(tz);
		}
		tzId = this.getTransportZoneId(tz.getDisplay_name());
		return tzId;
	}
	
	
	/**
	 * Add an TransportZone
	 * @param tz
	 */
	public void addTransportZone(TransportZone tz){
		service.addObject(tz, url);
	}
	
	/**
	 * delete specific TransportZone
	 * @param transportZoneName
	 */
	public void deleteTransportZone(String transportZoneName){
		String tzid = this.getTransportZoneId(transportZoneName);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	/**
	 * modify the specific TransportZone
	 * @param transportZoneName
	 * @param newTZ
	 */
	public void modifyTransportZone(String transportZoneName, TransportZone newTZ){
		//Create a TransportZone for Edit
		JsonUtils jsonUtils = new JsonUtils();
		String transportZonesInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = transportZoneName;
		String targetPropertyName = "_revision";

		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(transportZonesInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
				
		TransportZone finalTZ = newTZ;
		finalTZ.set_revision(_revision);
		
		//generate the edit url
		String tzid = this.getTransportZoneId(transportZoneName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalTZ, modifyUrl);
	}
	
	/**
	 * query the specific TransportZone info
	 * @param transportZoneName
	 * @return
	 */
	public String queryTransportZone(String transportZoneName){
		String tzid = this.getTransportZoneId(transportZoneName);
		String queryUrl = url + tzid;
		return service.queryObject(queryUrl);
	}
	
	
	public boolean isExist(String transportZoneName){
		VerifyUtils verifyUtils = new VerifyUtils();
		String jsonString = service.queryObject(url);
		String targetPropertyName = "display_name";
		boolean b = verifyUtils.isTargetExist(jsonString, targetPropertyName, transportZoneName);
		return b;
	}
	
	public void setupDefaultTransportZone(){
		TransportZone tz = null;
		if(!this.isExist(display_name)){
			tz = this.getDefaultTransportZone();
			this.addTransportZone(tz);
		}
		
		if(!this.isExist(display_name)){
			assert false: "Failed to add TransportZone!";
		}
	}
	
	public void cleanDefaultTransportZone(){
		if(this.isExist(display_name)){
			this.deleteTransportZone(display_name);
		}
	}
	
}
