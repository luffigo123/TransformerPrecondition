package com.vmware.transformer.PreCondition.Fabric;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Fabric.HostNodeService;

public class ESXiHost_Delete_ByCommand {
	@Test
	public void deleteESXiHost_ByCommand(){
		HostNodeService hostNodeService = new HostNodeService();
//		hostNodeService.setup_defaultESXiHostByCommand();
	}
}
