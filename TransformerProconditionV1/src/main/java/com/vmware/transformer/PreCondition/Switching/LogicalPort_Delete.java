package com.vmware.transformer.PreCondition.Switching;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Switching.LogicalPortService;

public class LogicalPort_Delete {
	@Test
	public void deleteLogicalPort(){
		LogicalPortService logicalPortService = new LogicalPortService();
		logicalPortService.cleanupLogicalPort();
	}
}
