package com.vmware.transformer.PreCondition.DHCP;

import org.testng.annotations.Test;

import com.vmware.transformer.service.DHCP.DHCPProfileService;

public class DHCPProfile_Add {
	
	@Test
	public void addDHCPProfile(){
		DHCPProfileService dhcpProfileService = new DHCPProfileService();
		dhcpProfileService.setup_Default_DHCPProfile();
	}
	
}
