package com.vmware.transformer.PreCondition.Fabric;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Fabric.EdgeNodeService;

public class EdgeHostByCommand_Add {
	
	@Test
	public void addEdgeHost(){
		EdgeNodeService edgeNodeService = new EdgeNodeService();
		edgeNodeService.setup_EdgeNode_ByRegisterCommand();
	}
	
}
