package com.vmware.transformer.PreCondition.DHCP;

import org.testng.annotations.Test;

import com.vmware.transformer.service.DHCP.DHCPRelayProfileService;

public class DHCPRelayProfile_Add {
	
	@Test
	public void addDHCPRelayProfile(){
		DHCPRelayProfileService dhcpRelayProfileService = new DHCPRelayProfileService();
		dhcpRelayProfileService.setup_DHCPRelayProfile();
	}
}
