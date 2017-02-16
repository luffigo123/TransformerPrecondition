package com.vmware.transformer.service.Inventory;

import java.util.ArrayList;

import com.vmware.transformer.model.Inventory.IPSets;
import com.vmware.transformer.model.Inventory.MacSets;
import com.vmware.transformer.model.Inventory.Member;
import com.vmware.transformer.model.Inventory.MemberCriteria;
import com.vmware.transformer.model.Inventory.NSGroup;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.service.Switching.LogicalPortService;
import com.vmware.transformer.service.Switching.LogicalSwitchService;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.GetInputString;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class NSGroupService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	//set default NSGroup dsplay_name
	public String display_name;
	
	public IPSetsService ipsetService = null;
	public MacSetsService macSetService = null;
	public LogicalSwitchService logicalSwitchService = null;
	public LogicalPortService logicalPortService = null;

	
	public NSGroupService() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/ns-groups/";
		
//		display_name = "NSGroup" + TestData.NativeString;
		
		ipsetService = new IPSetsService();
		macSetService = new MacSetsService();
		logicalSwitchService = new LogicalSwitchService();
		logicalPortService = new LogicalPortService();
		
		display_name = GetInputString.getInputString();
	}

	public NSGroup getDefaultNSGroup(){
		//log.info("Step 1: Setup Precondition: IPSet, MacSet");
		this.setup_PreconditionENV();
		//log.info("Step 1-1: Get IPSetId");
		IPSets defaultIPSets = this.ipsetService.getDefaultIPSets();
		String defaultIPSetsName = defaultIPSets.getDisplay_name();
		String ipSetId = this.ipsetService.getIpSetsId(defaultIPSetsName);
		//log.info("Step 1-2: Get MacSetId");
		MacSets defaultMacSet = this.macSetService.getDefaultMacSets();
		String defaultMacsetName = defaultMacSet.getDisplay_name();
		String macSetId = this.macSetService.getObjectId(defaultMacsetName);
//		log.info("Step 1-3: Get logical switch Id");
		String logicalSwitchId = this.logicalSwitchService.getDefaultLogicalSwitchId();
//		log.info("Step 1-4:Get logical port Id");
		String logicalPortId = this.logicalPortService.getDefaultLogicalPortId();
		
		//log.info("Step 2: Create member");
		ArrayList<Member> memberList = new ArrayList<Member>();
		Member member_1 = new Member("NSGroupSimpleExpression","IPSet","id","EQUALS",ipSetId);
		Member member_2 = new Member("NSGroupSimpleExpression","MACSet","id","EQUALS",macSetId);
		Member member_3 = new Member("NSGroupSimpleExpression","LogicalSwitch","id","EQUALS",logicalSwitchId);
		Member member_4 = new Member("NSGroupSimpleExpression","LogicalPort","id","EQUALS",logicalPortId);
		memberList.add(member_1);
		memberList.add(member_2);
		memberList.add(member_3);
		memberList.add(member_4);
		
		//log.info("Step 3: Create member_criteria");
		ArrayList<MemberCriteria> memberCriteria_List = new ArrayList<MemberCriteria>();
		MemberCriteria memberCriteria1 = new MemberCriteria("NSGroupTagExpression", "LogicalSwitch", this.display_name, "EQUALS", this.display_name, "EQUALS");
		MemberCriteria memberCriteria2 = new MemberCriteria("NSGroupTagExpression", "LogicalPort", this.display_name, "EQUALS", this.display_name, "EQUALS");
		memberCriteria_List.add(memberCriteria1);
		memberCriteria_List.add(memberCriteria2);
		
		NSGroup nsGroup = new NSGroup(this.display_name,this.display_name, memberList,memberCriteria_List);
		
		return nsGroup;
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
	

	public String getDefaultNSGroupId(){
		NSGroup defaultNSGroup = this.getDefaultNSGroup();
		String defaultNSGroupname = defaultNSGroup.getDisplay_name();
		if(!this.isExist(defaultNSGroupname)){
			this.addNSGroup(defaultNSGroup);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String defaultNSGroupId = this.getObjectId(defaultNSGroupname);
		return defaultNSGroupId;
	}
	
	
	
	/**
	 * Add a NSGroup
	 * @param nsGroup
	 */
	public void addNSGroup(NSGroup nsGroup){
		service.addObject(nsGroup, url);
	}
	
	
	/**
	 * 
	 * @param orgNSGroupName
	 * @param newNSGroup
	 */
	public void modifyNSGroup(String orgNSGroupName, NSGroup newNSGroup){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgNSGroupName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		NSGroup nsGroup = newNSGroup;
		nsGroup.set_revision(_revision);
		
		//generate the edit url
		String tzid = this.getObjectId(orgNSGroupName);
		String modifyUrl = url + tzid;
		service.modifyObject(nsGroup, modifyUrl);
	}
	
	/**
	 * delete specific NSGroup
	 * @param displayName
	 */
	public void deleteNSGroup(String displayName){
		String tzid = this.getObjectId(displayName);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	
	/**
	 * query the specific NSGroup info
	 * @param displayName
	 * @return
	 */
	public String queryNSGroup(String displayName){
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
	
	public void setup_PreconditionENV(){
		this.ipsetService.setup_defaultIPSet();
		this.macSetService.setup_defaultMacSet();
		this.logicalPortService.setupLogicalPort();
	}
	
	public void cleanup_PreconditionENV(){
		this.ipsetService.cleanup_defaultIPset();
		this.macSetService.cleanup_defaultMacSet();
		this.logicalPortService.cleanupLogicalPort();
	}
	
	public void setup_defaultNSGroup(){
		this.setup_PreconditionENV();
		if(!this.isExist(this.display_name)){
			this.addNSGroup(this.getDefaultNSGroup());
		}
		
		if(!this.isExist(display_name)){
			assert false: "Failed to add NSGroup";
		}
	}
	
	public void cleanup_defaultNSGroup(){
		if(this.isExist(this.display_name)){
			this.deleteNSGroup(this.display_name);
		}
		
		this.cleanup_PreconditionENV();
		
		if(this.isExist(display_name)){
			assert false: "Failed to delete NSGroup";
		}
	}
}
