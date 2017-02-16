package com.vmware.transformer.PreCondition.Switching;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Switching.LogicalSwitchService;

public class LogicalSwitch_Delete {
	@Test
	public void addLogicalSwitch(){
		LogicalSwitchService logicalSwitchService = new LogicalSwitchService();
		logicalSwitchService.cleanupLogicalSwitch_WithoutSwitchingProfiles();
	}
}
