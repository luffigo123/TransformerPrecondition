package com.vmware.transformer.PreCondition.Fabric;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Fabric.EdgeNodeService;

public class EdgeHost_Delete {
	
	@Test
	public void deleteEdgeHost(){
		EdgeNodeService edgeNodeService = new EdgeNodeService();
//		edgeNodeService.cleanup_defaultEdgeNode();
	}
}
