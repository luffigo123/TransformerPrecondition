package com.vmware.transformer.service.Switching;

import com.vmware.transformer.model.Switching.LogicalPort;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class LogicalPortServicePreCon {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	
	public LogicalPortServicePreCon() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/logical-ports/";
		
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
	
	public void addLogicalPort(LogicalPort logicalPort){
		service.addObject(logicalPort, url);
	}
	

	public void modifyLogicalPort(String orgLogicalPortName, LogicalPort newLogicalPort){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgLogicalPortName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		LogicalPort finalObject = newLogicalPort;
		finalObject.set_revision(_revision);
		
		//generate the edit url
		String tzid = this.getObjectId(orgLogicalPortName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);
	}
	
	public void deleteLogicalPort(String displayName){
		String tzid = this.getObjectId(displayName);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	public void deleteLogicalPortWithAttach(String displayName){
		String tzid = this.getObjectId(displayName);
		String deleteUrl = url + tzid + "?detach=true";
		service.deleteObject(deleteUrl);
	}
	
	public String queryLogicalPortInfo(String displayName){
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
	
	public LogicalPort getLogicalPort(String logicalPortName, String logicalSwitchId){
//		Attachment attachment = new Attachment("id001", "VIF");
//		LogicalPort logicalPort = new LogicalPort(display_name, display_name, defaultLogicalSwitchId, "UP", attachment);
		LogicalPort logicalPort = new LogicalPort(logicalPortName, logicalPortName, logicalSwitchId, "UP", null);
		return logicalPort;
	}
	

	

}
