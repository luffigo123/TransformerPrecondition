package com.vmware.transformer.PreCondition.Routing;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Routing.RouterPortService;

public class DownlinkLogicalPort_onTier1Router_Add {
	@Test
	public void addDownlinkLogicalPort_onTier1Router(){
		String tierType = "Tier1";
		RouterPortService routerPortService = new RouterPortService();
		routerPortService.setup_DefaultDownlink_LogicalRouterPort_OnRouter(tierType);
	}
}
