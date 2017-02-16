package com.vmware.transformer.PreCondition.Routing;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Routing.StaticRouterService;

public class StaticRouteWithDownlinkPort_onTier0Router_Delete {
	
	@Test
	public void deleteStaticRouteWithDownlinkport_onTier0(){
		String tierType = "Tier0";
		StaticRouterService staticRouterService = new StaticRouterService(tierType);
		staticRouterService.cleanUpDefault_StaticRoute_WithDownlinkPort_ENV();
	}
	
}
