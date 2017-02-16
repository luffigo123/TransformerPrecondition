package com.vmware.transformer.model.Fabric;

public class TransportZoneEndpoint {
	private String transport_zone_id;

	public String getTransport_zone_id() {
		return transport_zone_id;
	}

	public void setTransport_zone_id(String transport_zone_id) {
		this.transport_zone_id = transport_zone_id;
	}
	/**
	 * 
	 * @param transport_zone_id
	 */
	public TransportZoneEndpoint(String transport_zone_id) {
		super();
		this.transport_zone_id = transport_zone_id;
	}
	
}
