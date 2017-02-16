package com.vmware.transformer.model.Fabric;

public class HostSwitchProfileId {
	private String value;
	private String key;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * 
	 * @param value
	 * @param key		UplinkHostSwitchProfile
	 */
	public HostSwitchProfileId(String value, String key) {
		super();
		this.value = value;
		this.key = key;
	}
	
}
