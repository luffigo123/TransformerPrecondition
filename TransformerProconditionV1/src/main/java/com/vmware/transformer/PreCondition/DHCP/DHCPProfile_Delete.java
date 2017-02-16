package com.vmware.transformer.PreCondition.DHCP;

import org.testng.annotations.Test;

import com.vmware.transformer.service.DHCP.DHCPProfileService;

public class DHCPProfile_Delete {
	@Test
	public void deleteDHCPProfile(){
		DHCPProfileService dhcpProfileService = new DHCPProfileService();
		dhcpProfileService.clean_Default_DHCPProfile();
	}
}
