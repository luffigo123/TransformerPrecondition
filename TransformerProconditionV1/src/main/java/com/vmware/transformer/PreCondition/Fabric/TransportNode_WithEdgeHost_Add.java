package com.vmware.transformer.PreCondition.Fabric;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Fabric.TransportNodeService;

public class TransportNode_WithEdgeHost_Add {
	
	@Test
	public void addTransportNode_WithEdgeHost(){
		TransportNodeService transportNodeService = new TransportNodeService();
		transportNodeService.setup_defaultTransNode_withEdgeNode();
	}
	
}
