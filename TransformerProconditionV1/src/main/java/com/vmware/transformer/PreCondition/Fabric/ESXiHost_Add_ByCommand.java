package com.vmware.transformer.PreCondition.Fabric;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Fabric.HostNodeService;

public class ESXiHost_Add_ByCommand {
	@Test
	public void addESXiHost_byCommand(){
		HostNodeService hostNodeService = new HostNodeService();
		hostNodeService.setup_defaultESXiHostByCommand();
	}
}
