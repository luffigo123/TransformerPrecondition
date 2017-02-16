package com.vmware.transformer.PreCondition.Routing;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Routing.RoutingService;

public class Tier1Router_Delete {
	
	@Test
	public void deleteTier1Router(){
		String tierType = "Tier1";
		RoutingService routingService = new RoutingService();
		routingService.clean_Default_LogicalRouter(tierType);
	}
	
}
