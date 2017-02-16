package com.vmware.transformer.service.Firewall;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.vmware.transformer.model.Firewall.ApplyTo;
import com.vmware.transformer.model.Firewall.Section;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.service.Inventory.NSGroupService;
import com.vmware.transformer.service.Switching.LogicalPortService;
import com.vmware.transformer.service.Switching.LogicalSwitchService;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.Log4jInstance;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.TestData;
import com.vmware.transformer.utils.VerifyUtils;

@SuppressWarnings("serial")
public class SectionService implements Serializable{
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	public String display_name = "";
	private Logger logger = Log4jInstance.getLoggerInstance();
	
	NSGroupService nvGroupService = null;
	LogicalSwitchService lsService = null;
	LogicalPortService lpService = null;
	
	public SectionService() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/firewall/sections/";
		
		nvGroupService = new NSGroupService();
		lsService = new LogicalSwitchService();
		lpService = new LogicalPortService();
		
		display_name = "Section001" + TestData.NativeString;
	}
	
	/**
	 * Get default Section instance
	 * @param layer		LAYER3 | LAYER2
	 * @return
	 */
	public Section getDefaultSection(String layer){
		ArrayList<ApplyTo> list = this.getApplytoList();
		String stateful = "";
		if(layer.equalsIgnoreCase("layer3")){
			stateful = "true";
		}else{
			stateful = "false";
		}
		Section section = new Section(display_name, display_name, layer, stateful, list);
		return section;
	}
	
	/**
	 * get specific objectId
	 * @param displayName
	 * @return
	 */
	public String getObjectId(String displayName, String layer){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url + "?type=" + layer);
		String promptPropertyName = "display_name";
		String promptPropertyValue = displayName;
		String targetPropertyName = "id";
		String tzid = jsonUtils.getPropertyValue(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName);
		return tzid;
	}
	
	/**
	 * Add a Section
	 * @param section
	 */
	public void addSection(Section section){
		service.addObject(section, url);
	}
	
	
	/**
	 * modify the specific Section
	 * @param orgSectionName
	 * @param newSection
	 */
	public void modifySection(String orgSectionName, Section newSection, String layer){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = this.querySectionsInfo(layer);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgSectionName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		//Set new Section instance
		Section finalSection = newSection;
		finalSection.set_revision(_revision);
		
		//generate the edit url
		String tzid = this.getObjectId(orgSectionName, layer);
		String modifyUrl = url + tzid;
		service.modifyObject(finalSection, modifyUrl);

	}
	
	/**
	 * delete specific Section
	 * @param displayName
	 */
	public void deleteSection(String displayName, String layer){
		String tzid = this.getObjectId(displayName, layer);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	/**
	 * delete specific section
	 * @param displayName
	 * @param layer
	 */
	public void deleteSpecificSection(String displayName, String layer){
		if(this.isExist(displayName, layer)){
			this.deleteSection(displayName, layer);
		}
	}
	
	/**
	 * check the specific object whether exist by name
	 * @param displayName
	 * @return
	 */
	public boolean isExist(String displayName, String layer){
		VerifyUtils verifyUtils = new VerifyUtils();
		String jsonString = this.querySectionsInfo(layer);
		String targetPropertyName = "display_name";
		boolean b = verifyUtils.isTargetExist(jsonString, targetPropertyName, displayName);
		return b;
	}
	
	/**
	 * Get the section's info
	 * @param layer   LAYER2 | LAYER3
	 * @return
	 */
	public String querySectionsInfo(String layer){
		return service.queryObject(url + "?type=" + layer);
	}
	
	
	public ArrayList<ApplyTo> getApplytoList(){
		ArrayList<ApplyTo> list = new ArrayList<ApplyTo>();
		this.setupPrecondition();

		logger.info("Step 1-1: Check the default NSGroup whether exist, if not add it.");
		String defaultNSGroupId = nvGroupService.getDefaultNSGroupId();
		
		logger.info("Step 1-2: Generate the AppyTo object(NSGroup) list, add it to list.");		
		ApplyTo applyTo_NSGroup = new ApplyTo(nvGroupService.display_name, "true", "NSGroup", defaultNSGroupId);
		list.add(applyTo_NSGroup);
		
		
		logger.info("Step 2-1: Get the default LogicalSwitch, if it's not exist, add it.");
		String defaultLogicalSwitchId = lsService.getDefaultLogicalSwitchId();
		
		logger.info("Step 2-2: Generate the AppyTo object(LogicalSwitch) list, add it to list.");
		ApplyTo applyTo_logicalSwitch = new ApplyTo(lsService.display_name, "true", "LogicalSwitch", defaultLogicalSwitchId);
		list.add(applyTo_logicalSwitch);
		
		
		logger.info("Step 2-1: Get the default LogicalPort, if it's not exist, add it.");
		String defaultLogicalPortId = lpService.getDefaultLogicalPortId();
		
		logger.info("Step 2-2: Generate the AppyTo object(LogicalPort) list, add it to list.");
		ApplyTo applyTo_LogicalPort = new ApplyTo(lpService.display_name, "true", "LogicalPort", defaultLogicalPortId);
		list.add(applyTo_LogicalPort);
		
		return list;
	}
	
	public void setupPrecondition(){
		nvGroupService.setup_defaultNSGroup();
	}
	
	public void cleanupPrecondition(){
		nvGroupService.cleanup_defaultNSGroup();
	}
	
	public void setupSection(String layer){
		this.setupPrecondition();
		if(!this.isExist(this.display_name, layer)){
			this.addSection(this.getDefaultSection(layer));
		}
	}
	
	public void cleanupSection(String layer){
		if(this.isExist(this.display_name, layer)){
			this.deleteSection(this.display_name, layer);
		}
		this.cleanupPrecondition();
	}
}
