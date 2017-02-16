package com.vmware.transformer.PreCondition.Fabric;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Fabric.TransportNodeService;

public class TransportNode_WithESXiHost_Delete {
	
	@Test
	public void deleteTransportNode_WithESXiHost(){
		TransportNodeService transportNodeService = new TransportNodeService();
		transportNodeService.cleanup_defaultTransNode_withESXiHost_ByCommand();
	}
}
