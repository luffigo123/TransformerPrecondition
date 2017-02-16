package com.vmware.transformer.model.Routing;

import java.util.ArrayList;

public class Subnet {
	private ArrayList<String> ip_addresses;
	private String prefix_length;
	public ArrayList<String> getIp_addresses() {
		return ip_addresses;
	}
	public void setIp_addresses(ArrayList<String> ip_addresses) {
		this.ip_addresses = ip_addresses;
	}
	public String getPrefix_length() {
		return prefix_length;
	}
	public void setPrefix_length(String prefix_length) {
		this.prefix_length = prefix_length;
	}
	
	/**
	 * 
	 * @param ip_addresses
	 * @param prefix_length
	 */
	public Subnet(ArrayList<String> ip_addresses, String prefix_length) {
		super();
		this.ip_addresses = ip_addresses;
		this.prefix_length = prefix_length;
	}
	
	
}
