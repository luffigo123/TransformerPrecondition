package com.vmware.transformer.model.Fabric;

public class PhysicalNic {
	private String device_name;
	private String uplink_name;
	public String getDevice_name() {
		return device_name;
	}
	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}
	public String getUplink_name() {
		return uplink_name;
	}
	public void setUplink_name(String uplink_name) {
		this.uplink_name = uplink_name;
	}
	/**
	 * 
	 * @param device_name
	 * @param uplink_name
	 */
	public PhysicalNic(String device_name, String uplink_name) {
		super();
		this.device_name = device_name;
		this.uplink_name = uplink_name;
	}
	
}
