package com.vmware.transformer.model.Inventory;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class IPSets implements Serializable{
	private String display_name;
	private String description;
	private ArrayList<String> ip_addresses;
	private String resource_type;
	private String _revision;
	
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public ArrayList<String> getIp_addresses() {
		return ip_addresses;
	}
	public void setIp_addresses(ArrayList<String> ip_addresses) {
		this.ip_addresses = ip_addresses;
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
	 * @param ip_addresses		e.g. ["192.168.1.1-192.168.1.6","192.168.1.8","192.168.4.8/24"]
	 * @param resource_type		IPSet
	 */
	public IPSets(String display_name, String description,
			ArrayList<String> ip_addresses, String resource_type) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.ip_addresses = ip_addresses;
		this.resource_type = resource_type;
	}
}
