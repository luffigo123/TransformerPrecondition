package com.vmware.transformer.PreCondition.Fabric;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Fabric.HostNodeService;

public class ESXiHost_Add {
	
	@Test
	public void addESXiHost(){
		HostNodeService hostNodeService = new HostNodeService();
		hostNodeService.setup_defaultESXiHostByCommand();
	}

}
