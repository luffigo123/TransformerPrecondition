package com.vmware.transformer.PreCondition.Routing;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Routing.RoutingService;

public class Tier0Router_Delete {
	
	@Test
	public void deleteTier0Router(){
		String tierType = "Tier0";
		RoutingService routingService = new RoutingService();
		routingService.clean_Default_LogicalRouter(tierType);
	}
}
