package com.vmware.transformer.PreCondition.Routing;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Routing.RoutingService;

public class Tier1Router_Add {
	
	@Test
	public void addTier1Router(){
		String tierType = "Tier1";
		RoutingService routingService = new RoutingService();
		routingService.setupDefault_LogicalRouter(tierType);
	}
}
