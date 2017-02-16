package com.vmware.transformer.PreCondition.DHCP;

import org.testng.annotations.Test;

import com.vmware.transformer.service.DHCP.DHCPServerService;

public class DHCPService_Add{
		
	@Test
	public void addDHCPService(){
		DHCPServerService dhcpServerService = new DHCPServerService();
		dhcpServerService.setup_Default_DHCPService();
	}

}
