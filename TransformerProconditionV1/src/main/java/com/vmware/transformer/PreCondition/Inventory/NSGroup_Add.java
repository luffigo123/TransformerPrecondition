package com.vmware.transformer.PreCondition.Inventory;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Inventory.NSGroupService;

public class NSGroup_Add {
	@Test
	public void addNSGroup(){	
		NSGroupService nsGroupService = new NSGroupService();
		nsGroupService.setup_defaultNSGroup();
	}
}
