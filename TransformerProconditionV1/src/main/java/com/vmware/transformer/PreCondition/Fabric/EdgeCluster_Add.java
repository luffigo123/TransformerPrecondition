package com.vmware.transformer.PreCondition.Fabric;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Fabric.EdgeClusterService;

public class EdgeCluster_Add {
	
	@Test
	public void addEdgeCluster(){
		EdgeClusterService edgeClusterService = new EdgeClusterService();
		edgeClusterService.setupDefaultEdgeCluster();
	}
}
