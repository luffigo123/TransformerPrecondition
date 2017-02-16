package com.vmware.transformer.PreCondition.Fabric;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Fabric.EdgeClusterService;

public class EdgeCluster_Delete {
	@Test
	public void deleteEdgeCluster(){
		EdgeClusterService edgeClusterService = new EdgeClusterService();
		edgeClusterService.cleanup_DefaultEdgeCluster();
	}
}
