package com.vmware.transformer.model.DHCP;

import java.util.ArrayList;

public class DHCPRelayProfile {
	private String display_name;
	private String description;
	private ArrayList<String> server_addresses;
	private String resource_type;
	private String _revision;
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
	public ArrayList<String> getServer_addresses() {
		return server_addresses;
	}
	public void setServer_addresses(ArrayList<String> server_addresses) {
		this.server_addresses = server_addresses;
	}
	public String getResource_type() {
		return resource_type;
	}
	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}
	public String get_revision() {
		return _revision;
	}
	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	
	/**
	 * 
	 * @param display_name
	 * @param description
	 * @param server_addresses
	 * @param resource_type			DhcpRelayProfile
	 */
	public DHCPRelayProfile(String display_name, String description,
			ArrayList<String> server_addresses, String resource_type) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.server_addresses = server_addresses;
		this.resource_type = resource_type;
	}
}
