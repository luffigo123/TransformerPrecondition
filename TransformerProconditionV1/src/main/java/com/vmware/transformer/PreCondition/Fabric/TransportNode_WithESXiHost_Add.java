package com.vmware.transformer.PreCondition.Fabric;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Fabric.TransportNodeService;

public class TransportNode_WithESXiHost_Add {
	
	@Test
	public void addTransportNode_WithESXiHost(){
		TransportNodeService transportNodeService = new TransportNodeService();
//		transportNodeService.setup_defaultTransNode_withESXiHost_ByCommand();
		transportNodeService.setup_defaultTransNode_withESXiHost();
	}
	
}
