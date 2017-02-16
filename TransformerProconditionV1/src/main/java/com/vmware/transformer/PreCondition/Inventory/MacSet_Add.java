package com.vmware.transformer.PreCondition.Inventory;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Inventory.MacSetsService;

public class MacSet_Add {
	
	@Test
	public void addMacSet(){	
		MacSetsService macSetsService = new MacSetsService();
		macSetsService.setup_defaultMacSet();
	}
}
