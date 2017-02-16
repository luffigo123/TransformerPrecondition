package com.vmware.transformer.model.Fabric;

import java.util.ArrayList;

public class TransportNode {
	private String display_name;
	private String description;
	private String node_id;
	private String _revision;
	private ArrayList<TransportZoneEndpoint> transport_zone_endpoints;
	private ArrayList<HostSwitch> host_switches;
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNode_id() {
		return node_id;
	}
	public void setNode_id(String node_id) {
		this.node_id = node_id;
	}
	public String get_revision() {
		return _revision;
	}
	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	public ArrayList<TransportZoneEndpoint> getTransport_zone_endpoints() {
		return transport_zone_endpoints;
	}
	public void setTransport_zone_endpoints(
			ArrayList<TransportZoneEndpoint> transport_zone_endpoints) {
		this.transport_zone_endpoints = transport_zone_endpoints;
	}
	public ArrayList<HostSwitch> getHost_switches() {
		return host_switches;
	}
	public void setHost_switches(ArrayList<HostSwitch> host_switches) {
		this.host_switches = host_switches;
	}
	/**
	 * 
	 * @param display_name
	 * @param description
	 * @param node_id
	 * @param transport_zone_endpoints
	 * @param host_switches
	 */
	public TransportNode(String display_name, String description,
			String node_id,
			ArrayList<TransportZoneEndpoint> transport_zone_endpoints,
			ArrayList<HostSwitch> host_switches) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.node_id = node_id;
		this.transport_zone_endpoints = transport_zone_endpoints;
		this.host_switches = host_switches;
	}
}
