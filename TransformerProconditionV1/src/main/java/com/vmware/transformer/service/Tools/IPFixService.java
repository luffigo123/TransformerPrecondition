package com.vmware.transformer.service.Tools;

import java.util.ArrayList;

import com.vmware.transformer.model.Firewall.ApplyTo;
import com.vmware.transformer.model.Tools.IPFIX;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.service.Switching.LogicalPortService;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.TestData;
import com.vmware.transformer.utils.VerifyUtils;

public class IPFixService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;

	public LogicalPortService logicalPortService = null;
	public String dislayName = "";


	public IPFixService() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		logicalPortService = new LogicalPortService();
		
		url = "https://" + vmsIP + "/api/v1/ipfix/configs/";
		dislayName = "SwitchIPFixProfile001" + TestData.NativeString;
	}
	
	/**
	 * 
	 * @param display_name
	 * @return
	 */
	public String getObjectId(String display_name){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = display_name;
		String targetPropertyName = "id";
		String tzid = jsonUtils.getPropertyValue(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName);
		return tzid;
	}
	
	public void addIPFIX(IPFIX ipfix){
		service.addObject(ipfix, url);
	}
	
	/**
	 * 
	 * @param orgIPFIX_displayName
	 * @param newIPFIX
	 */
	public void modifyIPFIX(String orgIPFIX_displayName, IPFIX newIPFIX){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgIPFIX_displayName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		newIPFIX.set_revision(_revision);
		IPFIX finalObject = newIPFIX;
		
		//generate the edit url
		String tzid = this.getObjectId(orgIPFIX_displayName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);
	}
	
	/**
	 * 
	 * @param display_name
	 */
	public void deleteIPFIX(String display_name){
		String id = this.getObjectId(display_name);
		String deleteUrl = url + id;
		service.deleteObject(deleteUrl);
	}
	
	/**
	 * 
	 * @param display_name
	 * @return
	 */
	public String querySpecificIPFix(String display_name){
		String id = this.getObjectId(display_name);
		String queryUrl = url + id;
		return service.queryObject(queryUrl);
	}
	
	public String queryAllInfo(){
		return service.queryObject(url);
	}
	
	/**
	 * check the specific object whether exist by displayName
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
	
	public IPFIX getDefault_SwitchIPFixProfile(){
		String resource_type = "IpfixSwitchConfig";
		String packet_sample_probability = "0.1";
		String active_timeout = "300";
		String max_flows= "16384";
		String idle_timeout = "300";
		
		ArrayList<ApplyTo> applyTo_list = new ArrayList<ApplyTo>();
		
		IPFIX ipfix = new IPFIX(resource_type, this.dislayName, this.dislayName, active_timeout, idle_timeout, max_flows, packet_sample_probability, applyTo_list);
		return ipfix;
	}
	
	
	public void setPrecondition(){
		this.logicalPortService.setupLogicalPort();
	}
	
	public void cleanupPrecondition(){
		this.logicalPortService.cleanupLogicalPort();
	}
	
	public void setup_defaultIPFIX(){
		if(!this.isExist(this.dislayName)){
			this.addIPFIX(this.getDefault_SwitchIPFixProfile());
		}
	}
	
	public void cleanup_defaultIPFIX(){
		if(this.isExist(this.dislayName)){
			this.deleteIPFIX(this.dislayName);
		}
	}
	
}
