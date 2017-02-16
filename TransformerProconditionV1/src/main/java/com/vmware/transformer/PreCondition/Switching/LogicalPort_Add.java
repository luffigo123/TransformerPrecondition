package com.vmware.transformer.PreCondition.Switching;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Switching.LogicalPortService;

public class LogicalPort_Add {
	
	@Test
	public void addLogicalPort(){
		LogicalPortService logicalPortService = new LogicalPortService();
		logicalPortService.setupLogicalPort();
	}

}
