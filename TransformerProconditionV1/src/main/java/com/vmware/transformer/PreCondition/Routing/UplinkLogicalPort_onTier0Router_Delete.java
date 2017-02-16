package com.vmware.transformer.PreCondition.Routing;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Routing.RouterPortService;

public class UplinkLogicalPort_onTier0Router_Delete {
	
	@Test
	public void addUplinkLogicalPort_onTier0Router(){
		String tierType = "Tier0";
		RouterPortService routerPortService = new RouterPortService();
		routerPortService.clean_DefaultUplink_LogicalRouterPort_OnRouter(tierType);
	}
	
}
