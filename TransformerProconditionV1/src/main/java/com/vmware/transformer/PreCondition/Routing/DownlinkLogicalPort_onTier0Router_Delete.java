package com.vmware.transformer.PreCondition.Routing;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Routing.RouterPortService;

public class DownlinkLogicalPort_onTier0Router_Delete {
	@Test
	public void deleteDownlinkLogicalPort_onTier0Router(){
		String tierType = "Tier0";
		RouterPortService routerPortService = new RouterPortService();
		routerPortService.clean_DefaultDownlink_LogicalRouterPort_OnRouter(tierType);
	}
}
