package com.vmware.transformer.PreCondition.Inventory;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Inventory.IPSetsService;

public class IPSet_Delete {
	
	@Test
	public void deleteIPSet(){	
		IPSetsService ipSetService = new IPSetsService();
		ipSetService.cleanup_defaultIPset();
	}
}
