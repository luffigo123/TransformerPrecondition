package com.vmware.transformer.model.Inventory;

import java.util.ArrayList;

public class MacSets {
	private String display_name;
	private String description;
	private ArrayList<String> mac_addresses;
	private String resource_type;
	private String _revision;
	
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public ArrayList<String> getMac_addresses() {
		return mac_addresses;
	}
	public void setMac_addresses(ArrayList<String> mac_addresses) {
		this.mac_addresses = mac_addresses;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 
	 * @param display_name
	 * @param description
	 * @param mac_addresses		["01:23:45:67:89:ab","00:14:22:01:23:45"]
	 * @param resource_type		MACSet
	 */
	public MacSets(String display_name, String description,
			ArrayList<String> mac_addresses, String resource_type) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.mac_addresses = mac_addresses;
		this.resource_type = resource_type;
	}
	
	
	
	
	
}
