package com.vmware.transformer.PreCondition.Routing;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Routing.RouterPortService;

public class DownlinkLogicalPort_onTier0Router_Add {
	
	@Test
	public void addDownlinkLogicalPort_onTier0Router(){
		String tierType = "Tier0";
		RouterPortService routerPortService = new RouterPortService();
		routerPortService.setup_DefaultDownlink_LogicalRouterPort_OnRouter(tierType);
	}
}
