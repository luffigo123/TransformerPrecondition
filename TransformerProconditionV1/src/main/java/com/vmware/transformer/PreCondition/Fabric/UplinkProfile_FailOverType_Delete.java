package com.vmware.transformer.PreCondition.Fabric;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Fabric.UplinkProfilesService;

public class UplinkProfile_FailOverType_Delete {
	@Test
	public void deleteUplinkProfile(){	
		UplinkProfilesService uplinkProfilesService = new UplinkProfilesService();
		uplinkProfilesService.cleanup_defaultUplinkProfile_FailOverType();
	}
}
