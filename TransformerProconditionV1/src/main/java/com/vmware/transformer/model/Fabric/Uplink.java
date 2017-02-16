package com.vmware.transformer.model.Fabric;

public class Uplink {
	private String uplink_type;
	private String uplink_name;
	public String getUplink_type() {
		return uplink_type;
	}
	public void setUplink_type(String uplink_type) {
		this.uplink_type = uplink_type;
	}
	public String getUplink_name() {
		return uplink_name;
	}
	public void setUplink_name(String uplink_name) {
		this.uplink_name = uplink_name;
	}
	/**
	 * 
	 * @param uplink_type	PNIC
	 * @param uplink_name
	 */
	public Uplink(String uplink_type, String uplink_name) {
		super();
		this.uplink_type = uplink_type;
		this.uplink_name = uplink_name;
	}
	
}
