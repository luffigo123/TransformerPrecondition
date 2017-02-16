package com.vmware.transformer.PreCondition.Inventory;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Inventory.IPSetsService;
public class IPSet_Add{
	
	@Test
	public void addIPSet(){	
		IPSetsService ipSetService = new IPSetsService();
		ipSetService.setup_defaultIPSet();
	}
	
}
