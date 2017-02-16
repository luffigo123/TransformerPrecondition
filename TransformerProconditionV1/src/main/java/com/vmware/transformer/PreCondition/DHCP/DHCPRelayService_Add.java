package com.vmware.transformer.PreCondition.DHCP;

import org.testng.annotations.Test;

import com.vmware.transformer.service.DHCP.DHCPRelayServerService;

public class DHCPRelayService_Add {
	
	@Test
	public void addDHCPRelayService(){
		DHCPRelayServerService dhcpRelayServerService = new DHCPRelayServerService();
		dhcpRelayServerService.setup_defaultDHCPRelayServer();
	}
	
}
