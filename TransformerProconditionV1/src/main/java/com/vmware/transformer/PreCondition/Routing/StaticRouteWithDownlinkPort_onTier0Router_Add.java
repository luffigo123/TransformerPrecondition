package com.vmware.transformer.PreCondition.Routing;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Routing.StaticRouterService;

public class StaticRouteWithDownlinkPort_onTier0Router_Add {
	
	@Test
	public void addStaticRouteWithDownlinkport_onTier0(){
		String tierType = "Tier0";
		StaticRouterService staticRouterService = new StaticRouterService(tierType);
		staticRouterService.setupDefault_StaticRoute_WithDownlinkPort_ENV();
	}

}
