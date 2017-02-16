package com.vmware.transformer.PreCondition.Fabric;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Fabric.EdgeClusterProfileService;

public class EdgeClusterProfile_Add {
	
	@Test
	public void addEdgeClusterProfile(){
		EdgeClusterProfileService edgeClusterProfileService = new EdgeClusterProfileService();
		edgeClusterProfileService.setup_defaultEdgeClusterProfile();
	}
	
}
