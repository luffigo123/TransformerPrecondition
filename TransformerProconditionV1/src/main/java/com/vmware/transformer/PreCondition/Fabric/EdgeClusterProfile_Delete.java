package com.vmware.transformer.PreCondition.Fabric;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Fabric.EdgeClusterProfileService;

public class EdgeClusterProfile_Delete {
	
	@Test
	public void deleteEdgeClusterProfile(){
		EdgeClusterProfileService edgeClusterProfileService = new EdgeClusterProfileService();
		edgeClusterProfileService.cleanup_defaultEdgeClusterProfile();
	}
}
