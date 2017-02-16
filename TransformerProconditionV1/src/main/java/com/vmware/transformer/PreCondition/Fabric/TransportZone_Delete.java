package com.vmware.transformer.PreCondition.Fabric;

import org.testng.annotations.Test;

import com.vmware.transformer.service.Fabric.TransportZoneService;

public class TransportZone_Delete {
	@Test
	public void deleteTransportZone(){	
		TransportZoneService transportZoneService = new TransportZoneService();
		transportZoneService.cleanDefaultTransportZone();
	}
}
