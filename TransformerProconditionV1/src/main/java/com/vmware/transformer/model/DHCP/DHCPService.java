package com.vmware.transformer.model.DHCP;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DHCPService implements Serializable{
	private String display_name;
	private String description;
	private String dhcp_profile_id;
	private IPv4_DHCP_Server ipv4_dhcp_server;
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
	public String getDhcp_profile_id() {
		return dhcp_profile_id;
	}
	public void setDhcp_profile_id(String dhcp_profile_id) {
		this.dhcp_profile_id = dhcp_profile_id;
	}
	public IPv4_DHCP_Server getIpv4_dhcp_server() {
		return ipv4_dhcp_server;
	}
	public void setIpv4_dhcp_server(IPv4_DHCP_Server ipv4_dhcp_server) {
		this.ipv4_dhcp_server = ipv4_dhcp_server;
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
	 * @param dhcp_profile_id
	 * @param ipv4_dhcp_server
	 */
	public DHCPService(String display_name, String description,
			String dhcp_profile_id, IPv4_DHCP_Server ipv4_dhcp_server) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.dhcp_profile_id = dhcp_profile_id;
		this.ipv4_dhcp_server = ipv4_dhcp_server;
	}
}
