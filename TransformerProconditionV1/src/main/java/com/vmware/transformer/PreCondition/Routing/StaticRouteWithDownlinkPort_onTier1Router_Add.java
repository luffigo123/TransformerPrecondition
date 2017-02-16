package com.vmware.transformer.PreCondition.Routing;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Routing.StaticRouterService;

public class StaticRouteWithDownlinkPort_onTier1Router_Add {
	@Test
	public void addStaticRouteWithDownlinkport_onTier1(){
		String tierType = "Tier1";
		StaticRouterService staticRouterService = new StaticRouterService(tierType);
		staticRouterService.setupDefault_StaticRoute_WithDownlinkPort_ENV();
	}
}
