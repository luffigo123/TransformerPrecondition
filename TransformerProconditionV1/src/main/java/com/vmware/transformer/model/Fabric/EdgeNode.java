package com.vmware.transformer.model.Fabric;

import java.util.ArrayList;

public class EdgeNode {
	
	private String display_name;
	private String description;
	private ArrayList<String> ip_addresses;
	private String external_id;
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
	public String getExternal_id() {
		return external_id;
	}
	public void setExternal_id(String external_id) {
		this.external_id = external_id;
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
	 * @param ip_addresses
	 * @param external_id
	 * @param resource_type
	 */
	public EdgeNode(String display_name, String description,
			ArrayList<String> ip_addresses, String external_id,
			String resource_type) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.ip_addresses = ip_addresses;
		this.external_id = external_id;
		this.resource_type = resource_type;
	}
	/**
	 * 
	 * @param display_name
	 * @param description
	 * @param ip_addresses
	 * @param external_id
	 * @param resource_type
	 * @param _revision
	 */
	public EdgeNode(String display_name, String description,
			ArrayList<String> ip_addresses, String external_id,
			String resource_type, String _revision) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.ip_addresses = ip_addresses;
		this.external_id = external_id;
		this.resource_type = resource_type;
		this._revision = _revision;
	}

	
	
	

}
