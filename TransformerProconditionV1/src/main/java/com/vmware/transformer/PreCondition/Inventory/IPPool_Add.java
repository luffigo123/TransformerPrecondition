package com.vmware.transformer.PreCondition.Inventory;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Inventory.IPPoolService;

public class IPPool_Add {
	
	@Test
	public void addIPPool(){	
		IPPoolService ipPoolService = new IPPoolService();
		ipPoolService.setup_defaultIPPool();
	}
}
