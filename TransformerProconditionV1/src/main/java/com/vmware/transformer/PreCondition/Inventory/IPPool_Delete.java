package com.vmware.transformer.PreCondition.Inventory;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Inventory.IPPoolService;

public class IPPool_Delete {
	@Test
	public void deleteIPPool(){	
		IPPoolService ipPoolService = new IPPoolService();
		ipPoolService.cleanup_defaultIPPool();
	}
}
