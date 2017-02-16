package com.vmware.transformer.PreCondition.Fabric;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Fabric.TransportNodeService;

public class TransportNode_WithEdgeHost_Delete {
	
	@Test
	public void deleteTransportNode_WithEdgeHost(){
		TransportNodeService transportNodeService = new TransportNodeService();
		transportNodeService.cleanup_defaultTransNode_withEdgeNode();
	}
}
