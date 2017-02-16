package com.vmware.transformer.PreCondition.Routing;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Routing.RoutingService;

public class Tier0Router_Add {
	
	@Test
	public void addTier0Router(){
		String tierType = "Tier0";
		RoutingService routingService = new RoutingService();
		routingService.setupDefault_LogicalRouter(tierType);
	}
	
}
