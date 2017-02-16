package com.vmware.transformer.PreCondition.DHCP;

import org.testng.annotations.Test;

import com.vmware.transformer.service.DHCP.DHCPServerService;

public class DHCPService_Delete {
	@Test
	public void deleteDHCPService(){
		DHCPServerService dhcpServerService = new DHCPServerService();
		dhcpServerService.clean_Default_DHCPService();
	}
}
