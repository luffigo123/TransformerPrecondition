package com.vmware.transformer.model.Routing;

public class NextHop {
	private String ip_address;
	private String administrative_distance;
	private BindingService logical_router_port_id;
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getAdministrative_distance() {
		return administrative_distance;
	}
	public void setAdministrative_distance(String administrative_distance) {
		this.administrative_distance = administrative_distance;
	}
	public BindingService getLogical_router_port_id() {
		return logical_router_port_id;
	}
	public void setLogical_router_port_id(BindingService logical_router_port_id) {
		this.logical_router_port_id = logical_router_port_id;
	}
	/**
	 * 
	 * @param ip_address
	 * @param administrative_distance
	 * @param logical_router_port_id		BindingService
	 */
	public NextHop(String ip_address, String administrative_distance,
			BindingService logical_router_port_id) {
		super();
		this.ip_address = ip_address;
		this.administrative_distance = administrative_distance;
		this.logical_router_port_id = logical_router_port_id;
	}
	
	
}
