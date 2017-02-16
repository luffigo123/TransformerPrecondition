package com.vmware.transformer.PreCondition.Switching;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Switching.SwitchingProfilesService;

public class SwitchingProfiles_Add {
	
	@Test
	public void addSwitchingProfiles(){	
		SwitchingProfilesService switchingProfilesService = new SwitchingProfilesService();
		switchingProfilesService.setupSwitchingProfiles();
	}
}
