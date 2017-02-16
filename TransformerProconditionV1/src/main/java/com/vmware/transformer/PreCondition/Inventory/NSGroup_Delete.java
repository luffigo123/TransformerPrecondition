package com.vmware.transformer.PreCondition.Inventory;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Inventory.NSGroupService;

public class NSGroup_Delete {
	@Test
	public void deleteNSGroup(){	
		NSGroupService nsGroupService = new NSGroupService();
		nsGroupService.cleanup_defaultNSGroup();
	}
}
