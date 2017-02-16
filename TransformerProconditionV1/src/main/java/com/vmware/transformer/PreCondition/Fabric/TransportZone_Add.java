package com.vmware.transformer.PreCondition.Fabric;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Fabric.TransportZoneService;

public class TransportZone_Add {
	@Test
	public void addTransportZone(){	
		TransportZoneService transportZoneService = new TransportZoneService();
		transportZoneService.setupDefaultTransportZone();
	}
}
