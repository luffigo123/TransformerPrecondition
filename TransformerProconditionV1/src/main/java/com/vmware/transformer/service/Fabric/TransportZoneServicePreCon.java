package com.vmware.transformer.service.Fabric;

import com.vmware.transformer.model.Fabric.TransportZone;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;

public class TransportZoneServicePreCon {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
//	public String display_name;
//	public String hostswitchName;
	
	public TransportZoneServicePreCon() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/transport-zones/";
	
//		display_name = GetInputString.getInputString();
//		hostswitchName = display_name;
	}
	
	/**
	 * 
	 * @param displayName
	 * @param hostswitchName
	 * @param type				OVERLAY | VLAN
	 * @return
	 */
	public TransportZone getDefaultTransportZone(String displayName, String hostswitchName, String type){
		TransportZone tz = new TransportZone(displayName, hostswitchName, displayName, type);
		return tz;
	}
	

	/**
	 * Add an TransportZone
	 * @param tz
	 */
	public void addTransportZone(TransportZone tz){
		service.addObject(tz, url);
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
	
}
