package com.vmware.transformer.PreCondition.Switching;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Switching.SwitchingProfilesService;

public class SwitchingProfiles_Delete {
	@Test
	public void deleteSwitchingProfiles(){	
		SwitchingProfilesService switchingProfilesService = new SwitchingProfilesService();
		switchingProfilesService.cleanSwitchingProfiles();
	}
}
