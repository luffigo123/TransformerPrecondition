package com.vmware.transformer.PreCondition.Fabric;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Fabric.BridgesService;

public class BridgeCluster_Add {
	
	@Test
	public void addBridgeCluster(){
		BridgesService bridgesService = new BridgesService();
		bridgesService.setup_defaultBridgeService();
	}
}
