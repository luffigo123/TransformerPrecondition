package com.vmware.transformer.PreCondition.Fabric;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Fabric.HostNodeService;

public class ESXiHost_Delete {

	@Test
	public void deleteESXiHost(){
		HostNodeService hostNodeService = new HostNodeService();
//		hostNodeService.cleanup_defaultESXiHostByCommand();
	}
}
