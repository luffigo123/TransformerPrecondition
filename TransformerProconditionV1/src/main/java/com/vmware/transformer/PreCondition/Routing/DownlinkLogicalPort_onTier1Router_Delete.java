package com.vmware.transformer.PreCondition.Routing;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Routing.RouterPortService;

public class DownlinkLogicalPort_onTier1Router_Delete {
	@Test
	public void addDownlinkLogicalPort_onTier1Router(){
		String tierType = "Tier1";
		RouterPortService routerPortService = new RouterPortService();
		routerPortService.clean_DefaultDownlink_LogicalRouterPort_OnRouter(tierType);
	}
}
