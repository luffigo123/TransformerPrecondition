package com.vmware.transformer.PreCondition.Fabric;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Fabric.UplinkProfilesService;

public class UplinkProfile_FailOverType_Add {
	
	@Test
	public void addUplinkProfile(){	
		UplinkProfilesService uplinkProfilesService = new UplinkProfilesService();
		uplinkProfilesService.setup_defaultUplinkProfile_FailOverType();
	}
}
