package com.vmware.transformer.model.Inventory;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class IPPool implements Serializable{
	
	private ArrayList<Subnet> subnets;
	private String display_name;
	private String description;
	private String resource_type;
	private String _revision;

	public ArrayList<Subnet> getSubnets() {
		return subnets;
	}
	public void setSubnets(ArrayList<Subnet> subnets) {
		this.subnets = subnets;
	}
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
	public String get_revision() {
		return _revision;
	}
	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	
	/**
	 * 
	 * @param subnets
	 * @param display_name
	 * @param description
	 */
	public IPPool(ArrayList<Subnet> subnets, String display_name,
			String description) {
		super();
		this.subnets = subnets;
		this.display_name = display_name;
		this.description = description;
	}
}
