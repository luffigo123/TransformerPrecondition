package com.vmware.transformer.PreCondition.CombineComponent;

import org.testng.annotations.Test;

public class CombineComponentsCases {
	
	@Test(groups = {"CombineComponents"},alwaysRun=true)
	public void addRouterWithActiveActiveType(){
		CombineComponentsServer combineComponentsServer = new CombineComponentsServer();
		
		combineComponentsServer.setupIPPool();
		combineComponentsServer.setupTransportZone();
		combineComponentsServer.setupFabricUplinkProfiles();	
		combineComponentsServer.setupEdgeNodes();
		combineComponentsServer.setupESXiNode();		
		combineComponentsServer.setupTransportNode_WithEdgeNodes();
		combineComponentsServer.setupTransportNode_WithESXiNode();	
		combineComponentsServer.setupEdgeClusterProfile();		
		combineComponentsServer.setupEdgeClusters();	
		combineComponentsServer.setupLogicalSwitches();		
		combineComponentsServer.setupTier0Routers();
		combineComponentsServer.setupTier1Routers();	
		combineComponentsServer.setupLogicalPorts();
		combineComponentsServer.setupRouterPorts_withoutDHCPrePlayServer();
		combineComponentsServer.enableTier1RoutersAdvertisement();
		combineComponentsServer.setupRouteIPPrefix_OnTier0Routers();
		combineComponentsServer.setupRouterMap_OnTier0Routers();			
		combineComponentsServer.enableTier0RoutersRedistributionService();
		combineComponentsServer.addRedistributionCriteriaForTier0Routers();	
		combineComponentsServer.setupNATRules_OnRouters();
		combineComponentsServer.setupStaticRoutes();
		combineComponentsServer.setupBGPConfiguration();
		combineComponentsServer.setupBGPNeighborsOnRouters();
	}
	
}
