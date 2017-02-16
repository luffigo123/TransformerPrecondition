package com.vmware.transformer.PreCondition.Inventory;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Inventory.MacSetsService;

public class MacSet_Delete {
	@Test
	public void deleteMacSet(){	
		MacSetsService macSetsService = new MacSetsService();
		macSetsService.cleanup_defaultMacSet();
	}
}
