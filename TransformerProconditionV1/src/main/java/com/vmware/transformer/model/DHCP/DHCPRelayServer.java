package com.vmware.transformer.model.DHCP;

public class DHCPRelayServer {
	private String display_name;
	private String description;
	private String dhcp_relay_profile_id;
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
	public String getDhcp_relay_profile_id() {
		return dhcp_relay_profile_id;
	}
	public void setDhcp_relay_profile_id(String dhcp_relay_profile_id) {
		this.dhcp_relay_profile_id = dhcp_relay_profile_id;
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
	 * @param dhcp_relay_profile_id
	 * @param resource_type				DhcpRelayService
	 */
	public DHCPRelayServer(String display_name, String description,
			String dhcp_relay_profile_id, String resource_type) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.dhcp_relay_profile_id = dhcp_relay_profile_id;
		this.resource_type = resource_type;
	}
}
