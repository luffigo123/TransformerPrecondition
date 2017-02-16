package com.vmware.transformer.PreCondition.DHCP;

import org.testng.annotations.Test;

import com.vmware.transformer.service.DHCP.DHCPRelayServerService;

public class DHCPRelayService_Delete {
	@Test
	public void deleteDHCPRelayService(){
		DHCPRelayServerService dhcpRelayServerService = new DHCPRelayServerService();
		dhcpRelayServerService.cleanup_defaultDHCPRelayServer();
	}
}
