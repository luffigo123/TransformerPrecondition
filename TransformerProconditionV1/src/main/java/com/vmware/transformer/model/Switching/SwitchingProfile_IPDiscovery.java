package com.vmware.transformer.model.Switching;

public class SwitchingProfile_IPDiscovery implements SwitchingProfile {
	private String display_name;
	private String description;
	private String resource_type;
	private String arp_snooping_enabled;
	private String dhcp_snooping_enabled;
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
	public String getResource_type() {
		return resource_type;
	}
	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}
	public String getArp_snooping_enabled() {
		return arp_snooping_enabled;
	}
	public void setArp_snooping_enabled(String arp_snooping_enabled) {
		this.arp_snooping_enabled = arp_snooping_enabled;
	}
	public String getDhcp_snooping_enabled() {
		return dhcp_snooping_enabled;
	}
	public void setDhcp_snooping_enabled(String dhcp_snooping_enabled) {
		this.dhcp_snooping_enabled = dhcp_snooping_enabled;
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
	 * @param resource_type
	 * @param arp_snooping_enabled
	 * @param dhcp_snooping_enabled
	 */
	public SwitchingProfile_IPDiscovery(String display_name,
			String description, String resource_type,
			String arp_snooping_enabled, String dhcp_snooping_enabled) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.resource_type = resource_type;
		this.arp_snooping_enabled = arp_snooping_enabled;
		this.dhcp_snooping_enabled = dhcp_snooping_enabled;
	}
	
}
