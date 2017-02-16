package com.vmware.transformer.PreCondition.Fabric;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Fabric.BridgesService;

public class BridgeCluster_Delete {
	@Test
	public void deleteBridgeCluster(){
		BridgesService bridgesService = new BridgesService();
		bridgesService.cleanup_defaultBridgeService();
	}
}
