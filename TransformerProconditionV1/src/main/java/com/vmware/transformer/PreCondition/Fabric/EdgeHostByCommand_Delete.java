package com.vmware.transformer.PreCondition.Fabric;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Fabric.EdgeNodeService;

public class EdgeHostByCommand_Delete {
	
	@Test
	public void addEdgeHost(){
		EdgeNodeService edgeNodeService = new EdgeNodeService();
		edgeNodeService.cleanup_EdgeNode_ByRegisterCommand();
	}
}
